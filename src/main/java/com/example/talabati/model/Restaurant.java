package com.example.talabati.model;

import java.util.List;
import java.util.Set;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String address;

    @Column(nullable=false)
    private String phoneNumber;

    private Double rating;

    @OneToMany(mappedBy="restaurant",cascade=CascadeType.ALL,orphanRemoval=true)
    private Set<RestaurantSubCategory> restaurantSubCategories;

    @ManyToMany
     @JoinTable(name = "restaurants_categories",joinColumns = @JoinColumn(name = "restaurant_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<RestaurantCategory> categories;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private List<Rating> ratings;

    // Constructor
    public Restaurant() {
    }

    public Restaurant(String name, String address, String phoneNumber, Double rating, List<RestaurantCategory> categories, List<Rating> ratings) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
        this.categories = categories;
        this.ratings = ratings;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<RestaurantCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<RestaurantCategory> categories) {
        this.categories = categories;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
    public void updateFrom(Restaurant other) {
        if (other == null) {
            throw new IllegalArgumentException("The provided restaurant is null");
        }
    
        // Update basic fields
        if (StringUtils.isNotBlank(other.getName())) {
            this.name = other.getName().trim();
        }
        if (StringUtils.isNotBlank(other.getAddress())) {
            this.address = other.getAddress().trim();
        }
        if (StringUtils.isNotBlank(other.getPhoneNumber())) {
            this.phoneNumber = other.getPhoneNumber().trim();
        }
        if (other.getRating() != null) {
            this.rating = other.getRating();
        }
    
        // Update categories if provided
        if (other.getCategories() != null && !other.getCategories().isEmpty()) {
            this.categories.clear();
            this.categories.addAll(other.getCategories());
        }
    
        // Update ratings if provided
        if (other.getRatings() != null && !other.getRatings().isEmpty()) {
            this.ratings.clear();
            this.ratings.addAll(other.getRatings());
        }
    
        
    }
    
}
