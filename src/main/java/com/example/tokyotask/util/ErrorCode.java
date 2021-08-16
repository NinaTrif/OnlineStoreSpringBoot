package com.example.tokyotask.util;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    /**
     * Defines different error codes, so multiple exception classes are not needed.
     * Exceptions are differentiated by their error code.
     */
    INVALID_CUSTOMER("Invalid customer id.", HttpStatus.NOT_FOUND),
    INVALID_ITEM("Invalid item id.", HttpStatus.NOT_FOUND),
    INVALID_ORDER("Invalid order id.", HttpStatus.NOT_FOUND),
    ITEM_NOT_IN_ORDER("Item not in the order.", HttpStatus.NOT_FOUND),
    ORDER_NOT_ACTIVE("Order not acive.", HttpStatus.BAD_REQUEST);

    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    private HttpStatus status;
    private String message;

    public String getErrorCode() { return name(); }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
