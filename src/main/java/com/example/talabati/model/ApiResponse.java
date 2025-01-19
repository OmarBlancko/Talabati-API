package com.example.talabati.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ApiResponse<T> {

    @Getter
    @Setter
    private int statusCode;
    @Getter
    @Setter

    private String message;
    @Getter
    @Setter

    private T data; // Generic type to hold any kind of data

}
