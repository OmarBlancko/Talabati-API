package com.example.talabati.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.talabati.model.ApiResponse;
import com.example.talabati.model.Restaurant;
import com.example.talabati.service.RestaurantServices.RestaurantService;
import com.example.talabati.service.RestaurantServices.RestaurantSubCategoryService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import redis.clients.jedis.Response;





@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private final RestaurantService restaurantService;


    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

   
    @PostMapping
    public ResponseEntity<ApiResponse<Restaurant>> createRestuarant(@RequestBody Restaurant restaurant) {
       ApiResponse<Restaurant> response =  restaurantService.createRestaurnat(restaurant);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<Restaurant>> updateRestaurant(@PathVariable("id") long id, @RequestBody Restaurant restaurant) {
        ApiResponse<Restaurant> response = restaurantService.updateRestaurant(restaurant, id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> getMethodName(@PathVariable("id") long id) {
        restaurantService.deleteRestaurnat(id);
        ApiResponse<String> response = new ApiResponse<>(200, "Restaurant deleted successfully", null);

        return ResponseEntity.ok(response);
    }

    
}
