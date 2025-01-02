package com.example.talabati.service;

import org.springframework.stereotype.Service;

import com.example.talabati.repositories.RestaurantCategoryRepository;

@Service
public class CategoryService {
    private final RestaurantCategoryRepository restaurantCategoryRepository ;

    public CategoryService(RestaurantCategoryRepository restaurantCategoryRepository) {
        this.restaurantCategoryRepository = restaurantCategoryRepository;
    }
}
