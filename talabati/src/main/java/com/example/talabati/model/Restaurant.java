package com.example.talabati.model;

import java.util.List;

public class Restaurant {
    private int id;
    private String name;
    private String description;
    private String address;
    private List<String> phoneNumbers;
    private List<Integer> subCategoriesId;
    private List<Integer> productsIds;

    public Restaurant(int id, String name, String description, String address, List<String> phoneNumbers, List<Integer> subCategoriesId, List<Integer> productsIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.phoneNumbers = phoneNumbers;
        this.subCategoriesId = subCategoriesId;
        this.productsIds = productsIds;
    }

    public Restaurant() {
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<Integer> getSubCategoriesId() {
        return this.subCategoriesId;
    }

    public void setSubCategoriesId(List<Integer> subCategoriesId) {
        this.subCategoriesId = subCategoriesId;
    }

    public List<Integer> getProductsIds() {
        return this.productsIds;
    }

    public void setProductsIds(List<Integer> productsIds) {
        this.productsIds = productsIds;
    }
    

}
