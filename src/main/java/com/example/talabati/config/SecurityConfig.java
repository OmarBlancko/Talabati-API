package com.example.talabati.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.talabati.config.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
     private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(@org.springframework.context.annotation.Lazy JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
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

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/auth/**","/api/auth/login","api/auth/check-authentication    ").permitAll() // Allow unauthenticated access to login/signup

                .anyRequest().authenticated() // Require authentication for all other requests
                )
                .httpBasic().disable();// Add JWT filter
        

        return http.build();
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
