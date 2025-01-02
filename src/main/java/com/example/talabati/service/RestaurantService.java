package com.example.talabati.service;

import org.springframework.stereotype.Service;
import com.example.talabati.repositories.RestaurantRepository;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
}
