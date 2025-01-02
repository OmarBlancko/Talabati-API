package com.example.talabati.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.talabati.model.Product;
import com.example.talabati.repositories.ProductRepository;

@Service

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createOrder(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("product cannot be null");
        }
        return productRepository.save(product);
    }

    public Product UpdateProduct(Product product, Long id) {

        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        Product existingProduct = getProductById(id);
        product.setId(id);
        if (product.getName() != null && !product.getName().trim().isEmpty()) {
            existingProduct.setName(product.getName());
        }
        if (product.getPrice() > 0) {
            existingProduct.setPrice(product.getPrice());
        }
        if (product.getDescription() != null && !product.getDescription().trim().isEmpty()) {
            existingProduct.setDescription(product.getDescription());
        }
        if (product.getRestaurantId() > 0) {
            existingProduct.setRestaurantId(product.getRestaurantId());
        }
        if (!product.getSubCategories().isEmpty()) {
            existingProduct.setSubCategories(product.getSubCategories());
        }

        return productRepository.save(existingProduct);
    }

    public Product getProductById(Long id) {
        try {
            return productRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Couldn't retrieve product with ID: " + id));

        } catch (Exception e) {
            throw new RuntimeException("Couldn't retreive product" + e.getMessage());
        }

    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(long id) {

        productRepository.deleteById(id);
    }

}
