package com.example.talabati.config;

import java.security.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.talabati.config.filter.JwtAuthenticationFilter;
import com.example.talabati.controller.AuthController;
import com.example.talabati.service.JwtAuthenticationService;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
     private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationService jwtAuthenticationService;

    public SecurityConfig(@org.springframework.context.annotation.Lazy JwtAuthenticationFilter jwtAuthenticationFilter, JwtAuthenticationService jwtAuthenticationService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtAuthenticationService = jwtAuthenticationService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http
        //         .csrf(csrf -> csrf.disable())
        //         .authorizeHttpRequests(auth -> auth
        //         .requestMatchers("/api/auth/**").permitAll() // Public endpoints like login/register
        //         .anyRequest().authenticated() // All other endpoints require authentication
        //         ).formLogin(login -> login
        //         .loginPage("/login")
        //         .defaultSuccessUrl("/") // Redirect after login
        //         .permitAll()// Allow access to the login page
        //         )
        //         .logout(logout -> logout.permitAll());

        http
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/auth/**").permitAll() // Allow unauthenticated access to login/signup
                .anyRequest().authenticated() // Require authentication for all other requests
                )
                .httpBasic().disable()
                .formLogin().disable();// Add JWT filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
