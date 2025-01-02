package com.example.talabati.service;

import org.springframework.stereotype.Service;
import com.example.talabati.repositories.RestaurantSubCategoryRepository;

@Service
public class RestaurantSubCategoryService {
    private final RestaurantSubCategoryRepository restaurantSubCategoryRepository;

    public RestaurantSubCategoryService(RestaurantSubCategoryRepository restaurantSubCategoryRepository) {
        this.restaurantSubCategoryRepository = restaurantSubCategoryRepository;
    }
}
