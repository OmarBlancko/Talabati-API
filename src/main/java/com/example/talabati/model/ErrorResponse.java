package com.example.talabati.model;
public class ErrorResponse {
    @SuppressWarnings({"unused", "FieldMayBeFinal"})
    private String message;
    @SuppressWarnings({"unused", "FieldMayBeFinal"})
    private String details;
    public ErrorResponse(String message, String details) {
        this.message = message;
        this.details = details;
    }
}
