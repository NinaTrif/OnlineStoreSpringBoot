package com.example.tokyotask.services;


import com.example.tokyotask.dto.AddItemToOrderRequest;
import com.example.tokyotask.dto.CreateOrderRegisteredRequest;
import com.example.tokyotask.dto.RemoveItemFromOrderRequest;
import com.example.tokyotask.exceptions.BaseException;
import com.example.tokyotask.jpa_repositories.CustomerRepository;
import com.example.tokyotask.jpa_repositories.ItemRepository;
import com.example.tokyotask.jpa_repositories.OrderRepository;
import com.example.tokyotask.jpa_repositories.OrderedItemRepository;
import com.example.tokyotask.models.Customer;
import com.example.tokyotask.models.Item;
import com.example.tokyotask.models.Order;
import com.example.tokyotask.models.OrderedItem;
import com.example.tokyotask.util.ErrorCode;
import com.example.tokyotask.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    /**
     * Service Layer for handling Order operations.
     */
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderedItemRepository orderedItemRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new BaseException(ErrorCode.INVALID_ORDER));
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
        Optional<Customer> opt_customer = customerRepository.findById(customerId);
        if (!opt_customer.isPresent()) {
            throw new BaseException(ErrorCode.INVALID_CUSTOMER);
        }

        Customer customer = opt_customer.get();
        return orderRepository.findAllByCustomer(customer);
    }

    public void createOrder(CreateOrderRegisteredRequest orderRequest) {
        Optional<Customer> opt_customer = customerRepository.findById(orderRequest.getCustomerId());
        if (!opt_customer.isPresent()) {
            throw new BaseException(ErrorCode.INVALID_CUSTOMER);
        }

        Optional<Item> opt_item = itemRepository.findById(orderRequest.getItemId());
        if (!opt_item.isPresent()) {
            throw new BaseException(ErrorCode.INVALID_ITEM);
        }

        Item item = opt_item.get();
        Customer customer = opt_customer.get();
        List<OrderedItem> items = new ArrayList<>();
        Order order = new Order(Status.ACTIVE, item.getPrice(), customer, items);
        order = orderRepository.save(order);
        OrderedItem orderedItem = new OrderedItem(item, order, 1);
        orderedItem = orderedItemRepository.save(orderedItem);
        order.getOrderedItems().add(orderedItem);
        orderRepository.save(order);
    }

    public void addItemToOrder(AddItemToOrderRequest addItemRequest) {
        Optional<Order> opt_order = orderRepository.findById(addItemRequest.getOrderId());
        if (!opt_order.isPresent()) {
            throw new BaseException(ErrorCode.INVALID_ORDER);
        }

        Optional<Item> opt_item = itemRepository.findById(addItemRequest.getItemId());
        if (!opt_item.isPresent()) {
            throw new BaseException(ErrorCode.INVALID_ITEM);
        }

        Item item = opt_item.get();
        Order order = opt_order.get();

        if (order.getStatus() != Status.ACTIVE) {
            throw new BaseException(ErrorCode.ORDER_NOT_ACTIVE);
        }

        OrderedItem orderedItem = orderedItemRepository.findByOrderAndItem(order, item);
        if (orderedItem == null) {
            orderedItem = new OrderedItem(item, order, 1);
            orderedItem = orderedItemRepository.save(orderedItem);
            order.getOrderedItems().add(orderedItem);
            order.setPrice(order.getPrice() + item.getPrice());
            orderRepository.save(order);
        } else {
            orderedItem.setQuantity(orderedItem.getQuantity() + 1);
            orderedItemRepository.save(orderedItem);
            order.setPrice(order.getPrice() + item.getPrice());
            orderRepository.save(order);
        }
    }

    public void removeItemFromOrder(RemoveItemFromOrderRequest removeItemRequest) {
        Optional<Order> opt_order = orderRepository.findById(removeItemRequest.getOrderId());
        if (!opt_order.isPresent()) {
            throw new BaseException(ErrorCode.INVALID_ORDER);
        }
        Optional<Item> opt_item = itemRepository.findById(removeItemRequest.getItemId());
        if (!opt_item.isPresent()) {
            throw new BaseException(ErrorCode.INVALID_ITEM);
        }

        Item item = opt_item.get();
        Order order = opt_order.get();

        if (order.getStatus() != Status.ACTIVE) {
            throw new BaseException(ErrorCode.ORDER_NOT_ACTIVE);
        }

        OrderedItem orderedItem = orderedItemRepository.findByOrderAndItem(order, item);
        if (orderedItem == null) {
            throw new BaseException(ErrorCode.ITEM_NOT_IN_ORDER);
        }
        if (orderedItem.getQuantity() > 1) {
            orderedItem.setQuantity(orderedItem.getQuantity() - 1);
            orderedItemRepository.save(orderedItem);
            order.setPrice(order.getPrice() - item.getPrice());
            orderRepository.save(order);
        } else {
            orderedItemRepository.delete(orderedItem);
            order = orderRepository.getById(removeItemRequest.getOrderId());
            order.getOrderedItems().remove(orderedItem);
            if (order.getOrderedItems().size() == 0) {
                orderRepository.delete(order);
            } else {
                order.setPrice(order.getPrice() - item.getPrice());
                orderRepository.save(order);
            }
        }
    }

    public void placeOrder(Long id) {
        Optional<Order> opt_order = orderRepository.findById(id);
        if (!opt_order.isPresent()) {
            throw new BaseException(ErrorCode.INVALID_ORDER);
        }
        Order order = opt_order.get();
        if (order.getStatus() != Status.ACTIVE) {
            throw new BaseException(ErrorCode.ORDER_NOT_ACTIVE);
        }
        order.setStatus(Status.COMPLETED);
        orderRepository.save(order);
    }

    public void cancelOrder(Long id) {
        Optional<Order> opt_order = orderRepository.findById(id);
        if (!opt_order.isPresent()) {
            throw new BaseException(ErrorCode.INVALID_ORDER);
        }
        Order order = opt_order.get();
        if (order.getStatus() != Status.ACTIVE) {
            throw new BaseException(ErrorCode.ORDER_NOT_ACTIVE);
        }
        order.setStatus(Status.CANCELED);
        orderRepository.save(order);
    }

    public void deleteOrderById(Long id) {
        Optional<Order> opt_order = orderRepository.findById(id);
        if (!opt_order.isPresent()) {
            throw new BaseException(ErrorCode.INVALID_ORDER);
        }
        orderRepository.delete(opt_order.get());
    }
}
