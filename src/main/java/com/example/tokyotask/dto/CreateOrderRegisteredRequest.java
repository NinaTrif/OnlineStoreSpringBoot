package com.example.tokyotask.dto;

public class CreateOrderRegisteredRequest {
    /**
     * This class is used as DTO for creating orders.
     * It contains customer identification and identification of the first item that is added to the order.
     */
    private Long customerId;
    private Long itemId;

    public CreateOrderRegisteredRequest() {
    }

    public CreateOrderRegisteredRequest(Long customerId, Long itemId) {
        this.customerId = customerId;
        this.itemId = itemId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
