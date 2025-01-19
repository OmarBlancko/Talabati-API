package com.example.talabati.Exceptions;

import java.nio.file.AccessDeniedException;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.MethodNotAllowedException;

import com.example.talabati.model.ApiResponse;

@ControllerAdvice
@RestController
public class GlobalResponseHandler {

    // Handle general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception e) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle 404 Resource Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleResourceNotFound(ResourceNotFoundException e) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Resource not found", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Handle Bad Request (400)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleBadRequest(IllegalArgumentException e) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Invalid request", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handle 403 Forbidden - User lacks permission
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<String>> handleAccessDenied(AccessDeniedException e) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "Access Denied", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    // Handle 401 Unauthorized - User is not authenticated
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<String>> handleAuthenticationException(AuthenticationException e) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    // Handle Method Argument Validation Errors (e.g., validation annotations fail)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        StringBuilder message = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(error -> message.append(error.getDefaultMessage()).append(" "));
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Validation failed", message.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handle Validation Errors from @Valid or @Validated
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResponse<String>> handleBindException(BindException e) {
        StringBuilder message = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(error -> message.append(error.getDefaultMessage()).append(" "));
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Validation failed", message.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handle 409 Conflict - Resource conflict
    // @ExceptionHandler(ResourceConflictException.class)
    // public ResponseEntity<ApiResponse<String>> handleResourceConflict(ResourceConflictException e) {
    //     ApiResponse<String> response = new ApiResponse<>(HttpStatus.CONFLICT.value(), "Resource conflict", e.getMessage());
    //     return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    // }

    // Handle 500 Internal Server Error
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse<String>> handleNullPointer(NullPointerException e) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", "Null pointer exception occurred");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // // Handle custom exception (e.g., user not found)
    // @ExceptionHandler(UserNotFoundException.class)
    // public ResponseEntity<ApiResponse<String>> handleUserNotFound(UserNotFoundException e) {
    //     ApiResponse<String> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User not found", e.getMessage());
    //     return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    // }

    // Handle Method Not Allowed (405)
    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodNotAllowed(MethodNotAllowedException e) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.METHOD_NOT_ALLOWED.value(), "Method Not Allowed", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
