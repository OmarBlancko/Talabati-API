
package com.example.talabati.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

/**
 *
 * @author Blancko
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private double price;
    @Column(nullable=false)
    private String description;
    @Column(nullable=false)
     @ManyToMany
    @JoinTable(
        name = "product_sub_category",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "sub_category_id")
    )
    private Set<RestaurantSubCategory> subCategories;

    private long restaurantId;
    private boolean isAvailabe;
    private String imageUrl;

    public Product() {

    }

    public Product(long id, String name, double price, String description, Set<RestaurantSubCategory> subCategories, long restaurantId, boolean isAvailabe, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.subCategories = subCategories;
        this.restaurantId = restaurantId;
        this.isAvailabe = isAvailabe;
        this.imageUrl = imageUrl;
    }



    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }


    public boolean isIsAvailabe() {
        return this.isAvailabe;
    }

    public boolean getIsAvailabe() {
        return this.isAvailabe;
    }

    public void setIsAvailabe(boolean isAvailabe) {
        this.isAvailabe = isAvailabe;
    }


    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public Set<RestaurantSubCategory> getSubCategories() {
        return this.subCategories;
    }

    public void setSubCategories(Set<RestaurantSubCategory> subCategories) {
        this.subCategories = subCategories;
    }


}
