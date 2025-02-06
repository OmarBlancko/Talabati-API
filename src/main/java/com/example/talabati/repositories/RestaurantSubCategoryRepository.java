package com.example.talabati.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.talabati.model.Product;
import com.example.talabati.model.Restaurant;
import com.example.talabati.model.RestaurantSubCategory;
import java.util.Set;


@Repository
public interface RestaurantSubCategoryRepository extends JpaRepository<RestaurantSubCategory, Long> {
    // Set<RestaurantSubCategory>  findByRestauarant(Restaurant restaurant);
    // Set<Product> findBySubCategory(long sub_category_id);
}
