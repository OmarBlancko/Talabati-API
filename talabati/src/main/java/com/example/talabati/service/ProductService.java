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

    public Product createOrUpdateProduct(Product product) {
        return productRepository.save(product);
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
