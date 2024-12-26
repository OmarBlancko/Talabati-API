package com.example.talabati.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jdk.jshell.spi.ExecutionControl.RunException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle RuntimeException globally across all controllers
    @ExceptionHandler(RunException.class)

    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Global error : >>  " + ex.getMessage());
    }

    // Handle IllegalArgumentException globally across all controllers
    @ExceptionHandler(IllegalArgumentException.class)

    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: " + ex.getMessage());
    }

    
    // Handle any other exception

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + ex.getMessage());
    }
}
