package com.example.tokyotask.dto;

public class AddItemToOrderRequest {
    /**
     * This class is used as DTO for adding an item to an order.
     * It contains order and item identification.
     */
    private Long orderId;
    private Long itemId;

    public AddItemToOrderRequest() {
    }

    public AddItemToOrderRequest(Long orderId, Long itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
