package com.example.talabati.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class RestaurantCategory {
    // categories of resaurants Like ( Italians, Pizaa's, )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String name;
    @ManyToMany(mappedBy = "categories")
    private Set<Restaurant> restaurants;

    public RestaurantCategory() {
    }

    public RestaurantCategory(Long id, String name, Set<Restaurant> restaurants) {
        this.id = id;
        this.name = name;
        this.restaurants = restaurants;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Restaurant>  getRestaurants() {
        return this.restaurants;
    }

    public void setRestaurantIds(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

}
