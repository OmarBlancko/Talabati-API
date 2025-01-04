package com.example.talabati.controller.Exceptions;

public class OrderItemsNotFoundException extends RuntimeException {
    public OrderItemsNotFoundException(String message) {
        super(message);
    }
}