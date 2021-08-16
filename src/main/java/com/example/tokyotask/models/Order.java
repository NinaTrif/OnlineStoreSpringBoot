package com.example.tokyotask.models;

import com.example.tokyotask.util.Status;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {
    /**
     * This class is used to represent an order.
     */
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private Status status;
    private Double price;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderedItem> orderedItems;

    public Order() {}

    public Order(Status status, Double price, Customer customer, List<OrderedItem> orderedItems) {
        this.status = status;
        this.price = price;
        this.customer = customer;
        this.orderedItems = orderedItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", price=" + price +
                ", customer=" + customer +
                ", orderedItems=" + orderedItems +
                '}';
    }
}
