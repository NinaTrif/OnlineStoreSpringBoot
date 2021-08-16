package com.example.tokyotask.controllers;

import com.example.tokyotask.dto.AddItemToOrderRequest;
import com.example.tokyotask.dto.CreateOrderRegisteredRequest;
import com.example.tokyotask.dto.RemoveItemFromOrderRequest;
import com.example.tokyotask.models.Order;
import com.example.tokyotask.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {
    /**
     * Rest Controller used for creating, deleting and updating orders, as well as other order manipulations.
     * Order API Layer.
     *
     * A customer can create an order by adding one item to it. Once the order has been created, a customer can add different items to that order.
     * A customer can also remove items from an order, place an order or cancel/delete it.
     */
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public @ResponseBody ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity(orderService.getOrders(), HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public @ResponseBody ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return new ResponseEntity(orderService.getOrderById(id), HttpStatus.OK);
    }

    @GetMapping("/getOrdersByCustomer/{customerId}")
    public @ResponseBody ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable Long customerId) {
        return new ResponseEntity(orderService.getOrdersByCustomer(customerId), HttpStatus.OK);
    }

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRegisteredRequest orderRequest) {
        orderService.createOrder(orderRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/addItem")
    public ResponseEntity<?> addItemToOrder(@RequestBody AddItemToOrderRequest addItemRequest) {
        orderService.addItemToOrder(addItemRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/removeItem")
    public ResponseEntity<?> removeItemFromOrder(@RequestBody RemoveItemFromOrderRequest removeItemRequest) {
        orderService.removeItemFromOrder(removeItemRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/placeOrder/{id}")
    public ResponseEntity<?> placeOrder(@PathVariable Long id) {
        orderService.placeOrder(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/cancelOrder/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
