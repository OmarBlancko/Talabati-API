package com.example.talabati.service;

import org.springframework.stereotype.Service;

import com.example.talabati.repositories.RestaurantSubCategoryRepository;
@Service
public class RestaurantCategoryService {
private final RestaurantSubCategoryRepository restaurantSubCategoryRepository;

    public RestaurantCategoryService(RestaurantSubCategoryRepository restaurantSubCategoryRepository) {
        this.restaurantSubCategoryRepository = restaurantSubCategoryRepository;
    }
}
