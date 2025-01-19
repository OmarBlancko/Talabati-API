package com.example.talabati.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.talabati.Exceptions.ProductNotFoundException;
import com.example.talabati.model.ApiResponse;
import com.example.talabati.model.Product;
import com.example.talabati.repositories.ProductRepository;

@Service

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ApiResponse<Product> createProduct(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("product cannot be null");
        }
        Product productResponse = productRepository.save(product);
        ApiResponse<Product> response = new ApiResponse<>(200, "Product Created Successfully", productResponse);
        return response;
    }

    public ApiResponse<Product> UpdateProduct(Product product, Long id) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null ! invalid request !");
        }

        Product existingProduct = getProductById(id);
        product.setId(id);
        if (existingProduct == null) {
            throw new ProductNotFoundException("Product not found !");
        }
        checkRequestValidity(existingProduct, product);
        Product updatedProduct = productRepository.save(existingProduct);
        ApiResponse<Product> response = new ApiResponse<>(200, "Product updated successfully !", updatedProduct);
        return response;
    }

    public Product getProductById(Long id) {
        Optional<Product> optionaProduct = productRepository.findById(id);
        if (optionaProduct.get() == null) {

            throw new ProductNotFoundException("No product found with associated id >>>");
        } else {
            return optionaProduct.get();
        }

    }

    public ApiResponse<List<Product>> getAllProducts() {
        List<Product> responseList = productRepository.findAll();
        if (responseList.isEmpty()) {
            throw new ProductNotFoundException("No products found in DB");
        }
        ApiResponse<List<Product>> response = new ApiResponse<>(200, "Product fetched successfully !", responseList);
        return response;
    }

    public ApiResponse<?> deleteProduct(long id) {
        Product existingProduct = getProductById(id);
        if (existingProduct == null) {
            throw new ProductNotFoundException("No product found in DB with this id");

        }

        productRepository.deleteById(id);
        ApiResponse<Product> response = new ApiResponse<>(200, "Product deleted successfully !", null);
        return response;
    }

    public void checkRequestValidity(Product existingProduct, Product updatedProduct) {
        if (updatedProduct.getName() != null && !updatedProduct.getName().trim().isEmpty()) {
            existingProduct.setName(updatedProduct.getName());
        }
        if (updatedProduct.getPrice() > 0) {
            existingProduct.setPrice(updatedProduct.getPrice());
        }
        if (updatedProduct.getDescription() != null && !updatedProduct.getDescription().trim().isEmpty()) {
            existingProduct.setDescription(updatedProduct.getDescription());
        }
        if (updatedProduct.getRestaurantId() > 0) {
            existingProduct.setRestaurantId(updatedProduct.getRestaurantId());
        }
        if (!updatedProduct.getSubCategories().isEmpty()) {
            existingProduct.setSubCategories(updatedProduct.getSubCategories());
        }
    }

}
