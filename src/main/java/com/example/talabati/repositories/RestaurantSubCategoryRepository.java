package com.example.talabati.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.talabati.model.RestaurantSubCategory;

@Repository
public interface RestaurantSubCategoryRepository extends JpaRepository<RestaurantSubCategory, Long> {
}
