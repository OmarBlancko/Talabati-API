package com.example.talabati.service.RestaurantServices;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.talabati.model.ApiResponse;
import com.example.talabati.model.Product;
import com.example.talabati.model.RestaurantCategory;
import com.example.talabati.model.RestaurantSubCategory;
import com.example.talabati.repositories.RestaurantSubCategoryRepository;

public class RestaurantSubCategoryServiceImpl implements RestaurantSubCategoryService {

    @Autowired
    private final RestaurantSubCategoryRepository subCategoryRespository;

    public RestaurantSubCategoryServiceImpl(RestaurantSubCategoryRepository subCategoryRespository) {
        this.subCategoryRespository = subCategoryRespository;
    }

    @Override
    public RestaurantSubCategory createSubCategory(RestaurantSubCategory subCategory) {
        if (subCategory == null) {
            throw new IllegalArgumentException("subCategory cannot be null");

        }
        RestaurantSubCategory response = subCategoryRespository.save(subCategory);
        return response;
    }

    @Override
    public RestaurantSubCategory updateSubCategory(RestaurantSubCategory updatedSubCategory, long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSubCategory'");
    }

    @Override
    public void deleteRestaurnatSubCategory(RestaurantSubCategory subCategory) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRestaurnatSubCategory'");
    }

    @Override
    public Set<RestaurantSubCategory> getRestaurantSubCategorys(long restauarant_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRestaurantSubCategorys'");
    }

    @Override
    public Set<Product> getSubCategoryProducts(long sub_category_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSubCategoryProducts'");
    }

}
