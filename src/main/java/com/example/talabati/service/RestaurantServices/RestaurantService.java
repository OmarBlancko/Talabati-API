package com.example.talabati.service.RestaurantServices;

import java.util.List;

import com.example.talabati.model.ApiResponse;
import com.example.talabati.model.Restaurant;

public interface  RestaurantService {
    ApiResponse<Restaurant> createRestaurnat(Restaurant restaurant);
    ApiResponse<Restaurant> updateRestaurant(Restaurant restaurant,long id);
    void deleteRestaurnat(long id);
    Restaurant findByName(String name);
    List<Restaurant> findByCategory(long category_id);

}
