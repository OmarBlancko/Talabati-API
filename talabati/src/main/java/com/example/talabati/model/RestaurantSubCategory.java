package com.example.talabati.model;

import java.util.List;

public class RestaurantSubCategory {

    List<Restaurant> restaurants;
    SubCategory subCategory;

    public RestaurantSubCategory() {

    }

    public RestaurantSubCategory(List<Restaurant> restaurants, SubCategory subCategory) {
        this.restaurants = restaurants;
        this.subCategory = subCategory;
    }

    public List<Restaurant> getRestaurants() {
        return this.restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public SubCategory getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

}

