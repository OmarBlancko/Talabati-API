package com.example.talabati.service.RestaurantServices;


import java.util.Set;

import com.example.talabati.model.ApiResponse;
import com.example.talabati.model.Product;
import com.example.talabati.model.RestaurantSubCategory;

public interface RestaurantSubCategoryService {
    RestaurantSubCategory createSubCategory(RestaurantSubCategory subCategory);
    RestaurantSubCategory updateSubCategory(RestaurantSubCategory updatedSubCategory,long id);
    void deleteRestaurnatSubCategory(RestaurantSubCategory subCategory);
    Set<RestaurantSubCategory>  getRestaurantSubCategorys(long restauarant_id);
    Set<Product> getSubCategoryProducts(long sub_category_id);
}
