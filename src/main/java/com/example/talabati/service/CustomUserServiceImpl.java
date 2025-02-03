package com.example.talabati.service;

import com.example.talabati.model.ApiResponse;
import com.example.talabati.model.User;

public class CustomUserServiceImpl implements  UserService{

    public User findByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }

    @Override
    public ApiResponse<User> findByemail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByemail'");
    }

    @Override
    public ApiResponse<User> createUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }

    @Override
    public ApiResponse<User> updateUser(User user, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

}
