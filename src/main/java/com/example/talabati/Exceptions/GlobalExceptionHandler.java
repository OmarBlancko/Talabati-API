package com.example.talabati.Exceptions;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.talabati.model.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle RuntimeException globally across all controllers
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> handleRuntimeException(RuntimeException ex) {
        ApiResponse<String> response = new ApiResponse<>(500, "Unexpected  general runtime error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    // Handle IllegalArgumentException globally across all controllers
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiResponse<String> response = new ApiResponse<>(400, "Invalid input: " + ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Handle CustomDatabaseException globally across all controllers
    @ExceptionHandler(CustomDatabaseException.class)
    public ResponseEntity<ApiResponse<String>> handleDatabaseException(CustomDatabaseException ex) {
        ApiResponse<String> response = new ApiResponse<>(500, "Database error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    // Handle OrderNotFoundException specifically
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleOrderNotFound(OrderNotFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>(404, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    // Handle UserNotFoundException 
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> UserNotFoundException(UserNotFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>(404, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }
        // Handle UserNotFoundException 
        @ExceptionHandler(ProfileSettingsNotFound.class)
        public ResponseEntity<ApiResponse<String>> ProfileSettingsNotFound(ProfileSettingsNotFound ex) {
            ApiResponse<String> response = new ApiResponse<>(404, ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    
        }
    // Handle OrderItemsNotFoundException globally
    @ExceptionHandler(OrderItemsNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleOrderItemsNotFound(OrderItemsNotFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>(404, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handlePaymentNotFound(PaymentNotFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>(404, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
   // Handle ProductNotFoundException globally
   @ExceptionHandler(ProductNotFoundException.class)
   public ResponseEntity<ApiResponse<String>> handleProductNotFound(ProductNotFoundException ex) {
       ApiResponse<String> response = new ApiResponse<>(404, ex.getMessage(), null);
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
   }

    // Handle any other generic exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception ex) {
        LoggerFactory.getLogger(GlobalExceptionHandler.class).error("Unexpected error", ex);
        ApiResponse<String> response = new ApiResponse<>(500, "Unexpected error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
