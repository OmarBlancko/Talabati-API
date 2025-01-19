package com.example.talabati.controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.talabati.model.ApiResponse;
import com.example.talabati.model.Product;
import com.example.talabati.service.ProductService;

/**
 *
 * @author Blancko
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //// POST Method 
    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody Product product) {
        ApiResponse<Product> response = productService.createProduct(product);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

    //// GET specific product Method
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable("id") long id) {
        ApiResponse<Product> response = new ApiResponse<>(200, "Product fetched successfully !", productService.getProductById(id));
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    //// GET all products method
    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        ApiResponse<List<Product>> response = productService.getAllProducts();
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        ApiResponse<Product> response = productService.UpdateProduct(product, id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteProduct(@PathVariable("id") long id) {
        ApiResponse<?> response = productService.deleteProduct(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
