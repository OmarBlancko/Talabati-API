package com.example.talabati.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.talabati.model.RestaurantCategory;

public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory, Long> {

}
