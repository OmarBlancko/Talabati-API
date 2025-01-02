package com.example.talabati.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class RestaurantSubCategory {

    //every restaurant category 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
    @ManyToMany(mappedBy = "subCategories")
    private Set<Product> menuItemsList;
    public RestaurantSubCategory() {
    }

    public RestaurantSubCategory(Long id, String name, Restaurant restaurant, Set<Product> menuItemsList) {
        this.id = id;
        this.name = name;
        this.restaurant = restaurant;
        this.menuItemsList = menuItemsList;
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

    public Restaurant getRestauarant() {
        return this.restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Set<Product> getMenuItemsList() {
        return this.menuItemsList;
    }

    public void setMenuItemsList(Set<Product> menuItemsList) {
        this.menuItemsList = menuItemsList;
    }

}
