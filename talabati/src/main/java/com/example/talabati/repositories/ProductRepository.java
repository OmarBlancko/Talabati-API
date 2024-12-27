package com.example.talabati.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.talabati.model.Product;

public interface ProductRepository extends  JpaRepository<Product, Long> {
    
}
