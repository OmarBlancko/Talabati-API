package com.example.talabati.service.UserServices;

import java.util.HashSet;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.talabati.Exceptions.OrderNotFoundException;
import com.example.talabati.Exceptions.UserNotFoundException;
import com.example.talabati.model.ApiResponse;
import com.example.talabati.model.ProfileSettings;
import com.example.talabati.model.Role;
import com.example.talabati.model.User;
import com.example.talabati.repositories.ProfileSettingsRepository;
import com.example.talabati.repositories.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleService roleService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final ProfileSettingsRepository profileSettingsRepository;
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder,ProfileSettingsRepository profileSettingsRepository) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.profileSettingsRepository = profileSettingsRepository;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        
        if (user == null) {
            throw new UserNotFoundException("User with username" + username + "not found");
        } else {
                    Hibernate.initialize(user.getRoles());

             return user;

        }
    }

    @Override
    public ApiResponse<User> findByemail(String email) {
        User user = userRepository.findByUsername(email);
        if (user == null) {
            throw new UserNotFoundException("User with email" + email + "not found");
        } else {
            ApiResponse<User> response = new ApiResponse<>(200, "User fetched Successfully ", user);
            return response;

        }
    }

    // initial impl
    @Override
    public ApiResponse<User> createUser(@Valid User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already registered !");
        }
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username is already in use !");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Role defaultRole = roleService.findByName("USER");
            user.setRoles(new HashSet<>());
            user.getRoles().add(defaultRole);
        }
        if (user.getProfileSettings() == null) {
        user.setProfileSettings(new ProfileSettings(null, user));
    }
        User createdUser = userRepository.save(user);
        ProfileSettings  currentSettings = createdUser.getProfileSettings();
        currentSettings.setUser_id(createdUser.getId());
        profileSettingsRepository.save(currentSettings);
        return new ApiResponse<>(200, "User created Successfully ", createdUser);
    }

    @Override
    public ApiResponse<User> updateUser(User updatedUser, Long id) {
        if (updatedUser == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        User existingUser = getUserById(id);
        updatedUser.setId(id);
        checkRequestValidity(updatedUser, existingUser);
        existingUser.updateFrom(updatedUser);
        userRepository.save(existingUser);
        return new ApiResponse<>(200, "User updated successfully", existingUser);

    }

    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("User with ID " + id + " not found."));
    }

    public void checkRequestValidity(User updatedUser, User existingUser) {
        // Check if the username is not null or empty
        if (updatedUser.getUsername() != null && !updatedUser.getUsername().isEmpty()) {
            existingUser.setUsername(updatedUser.getUsername());
        }

        // Check if the email is not null or empty
        if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()) {
            existingUser.setEmail(updatedUser.getEmail());
        }

        // Check if the password is not null or empty (ensure it's encrypted when saving)
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));  // Hash the new password before saving
        }

        // Check if the phone number is not null or empty
        if (updatedUser.getPhoneNumber() != null && !updatedUser.getPhoneNumber().isEmpty()) {
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        }

        // Check if profile settings are provided
        if (updatedUser.getProfileSettings() != null) {
            existingUser.setProfileSettings(updatedUser.getProfileSettings());
        }

        // Check if roles are provided and update if needed
        if (updatedUser.getRoles() != null && !updatedUser.getRoles().isEmpty()) {
            existingUser.setRoles(updatedUser.getRoles());
        }
    }
}
