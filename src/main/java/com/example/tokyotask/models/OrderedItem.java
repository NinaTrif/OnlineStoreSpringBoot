package com.example.tokyotask.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
// @Table(name = "ORDER_ITEMS", uniqueConstraints = { @UniqueConstraint(columnNames = { "order", "item" }) })
public class OrderedItem {
    /**
     * This class is used to represent and item that has been added to an order, along with the quantity.
     */
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @ManyToOne
    private Item item;
    @ManyToOne
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;
    private Integer quantity;

    public OrderedItem() {}

    public OrderedItem(Item item, Order order, Integer quantity) {
        this.item = item;
        this.order = order;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderedItem{" +
                "id=" + id +
                ", item=" + item +
                ", order=" + order +
                ", quantity=" + quantity +
                '}';
    }
}
