package com.example.talabati.model;

public class Rating {
    private int id;
    private int restaurantId;
    private int userId;
    private int rate;
    private String review;

    public Rating() {
    }

    public Rating(int id, int restaurantId, int userId, int rate, String review) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.rate = rate;
        this.review = review;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRate() {
        return this.rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getReview() {
        return this.review;
    }

    public void setReview(String review) {
        this.review = review;
    }

}
