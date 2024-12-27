package com.example.talabati.model;

import java.util.List;
 
class SubCategory {
    private int id;
    private String name;
    private List<Integer> restaurantIds;

    public SubCategory() {
    }

    public SubCategory(int id, String name, List<Integer> restaurantIds) {
        this.id = id;
        this.name = name;
        this.restaurantIds = restaurantIds;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getRestaurantIds() {
        return this.restaurantIds;
    }

    public void setRestaurantIds(List<Integer> restaurantIds) {
        this.restaurantIds = restaurantIds;
    }
    
}