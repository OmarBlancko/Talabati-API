package com.example.talabati.service;


import com.example.talabati.model.ApiResponse;
import com.example.talabati.model.User;

public interface UserService{
     User findByUsername(String username);
     ApiResponse<User>  findByemail(String email);
     ApiResponse<User>  createUser(User user);
     ApiResponse<User>  updateUser(User user,Long id);
}