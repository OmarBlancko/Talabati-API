package com.example.talabati.service;

import org.springframework.stereotype.Service;
import com.example.talabati.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
