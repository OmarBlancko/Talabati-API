package com.example.talabati.service.JwtServices;

import java.util.Collections;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.talabati.config.Util.JwtUtil;
import com.example.talabati.model.User;
import com.example.talabati.service.UserService;

@Service
public class JwtAuthenticationService {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public JwtAuthenticationService(@Lazy UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public UserDetails loadUserByToken(String token) {
        String username = jwtUtil.extractUsername(token);
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Assuming user roles are stored as a String in the User object
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRoles()))
        );
    }

    public boolean validateToken(String token, String username) {
        return jwtUtil.isValidToken(token, username);
    }
}
