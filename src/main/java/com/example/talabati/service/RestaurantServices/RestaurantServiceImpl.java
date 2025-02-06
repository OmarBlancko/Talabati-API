package com.example.talabati.service.RestaurantServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.talabati.Exceptions.RestaurantNotFoundException;
import com.example.talabati.model.ApiResponse;
import com.example.talabati.model.Restaurant;
import com.example.talabati.repositories.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private final RestaurantRepository restaurantRepository;
    
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public ApiResponse<Restaurant> createRestaurnat(Restaurant restaurant) {
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurant cannot be null");
            
        }
        Restaurant response = restaurantRepository.save(restaurant);
        // if(restaurant.getCategories() !=null)
        return new ApiResponse<Restaurant>(200,"Restaurant Created Successfully  ", response);
    }
    @Override
    public ApiResponse<Restaurant> updateRestaurant(Restaurant updatedRestaurant, long id) {
        if (updatedRestaurant == null) {
            throw new IllegalArgumentException("Restaurant cannot be null");
        }
        Restaurant existingRestaurant = findById(id);
        updatedRestaurant.setId(id);
        existingRestaurant.updateFrom(updatedRestaurant);
        Restaurant response = restaurantRepository.save(existingRestaurant);
        return new ApiResponse<Restaurant>(200,"Restaurant updated Successfully  ", response);
    }
    @Override
    public void deleteRestaurnat(long id) {
        if (id == 0) {
            throw new IllegalArgumentException("id cannot be zero");

        }
        if(findById(id) == null) {
            throw new RestaurantNotFoundException("Restaurant not found !");

        }
        restaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant findByName(String name) {
        if(name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Restaurant cannot be null");

        }
        Optional<Restaurant> response = restaurantRepository.findByName(name);
        if(response.isEmpty()) {
            throw new RestaurantNotFoundException("Restaurant not found !");

        }
        return response.get();
    }

    @Override
    public List<Restaurant> findByCategory(long category_id) {
        Optional<List<Restaurant>> response =  Optional.ofNullable(restaurantRepository.findByCategory(category_id));
        if (response.isEmpty()) {
            throw new RestaurantNotFoundException("No Restaurants  founded in this category!");

        }
        return  response.get();
    }

    public Restaurant findById(long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant with ID " + id + " not found."));
    }



/* 
    void checkValidation(Restaurant updatedRestaurant,Restaurant existingRestaurant){
        if (StringUtils.isNotBlank(updatedRestaurant.getName())) {
            existingRestaurant.setName(updatedRestaurant.getName().trim());
        }

        if (StringUtils.isNotBlank(updatedRestaurant.getAddress())) {
            existingRestaurant.setAddress(updatedRestaurant.getAddress().trim());
        }

        if (StringUtils.isNotBlank(updatedRestaurant.getPhoneNumber())) {
            existingRestaurant.setPhoneNumber(updatedRestaurant.getPhoneNumber().trim());
        }

        if (updatedRestaurant.getRating() != null) {
            existingRestaurant.setRating(updatedRestaurant.getRating());
        }

        if (updatedRestaurant.getCategories() != null && !updatedRestaurant.getCategories().isEmpty()) {
            existingRestaurant.getCategories().clear();
            existingRestaurant.getCategories().addAll(updatedRestaurant.getCategories());
        }

        if (updatedRestaurant.getRatings() != null && !updatedRestaurant.getRatings().isEmpty()) {
            existingRestaurant.getRatings().clear();
            existingRestaurant.getRatings().addAll(updatedRestaurant.getRatings());
        }
    }
*/

}
