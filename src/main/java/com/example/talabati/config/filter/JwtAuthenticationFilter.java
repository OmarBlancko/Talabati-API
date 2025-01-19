package com.example.talabati.config.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.talabati.controller.AuthController;
import com.example.talabati.service.JwtAuthenticationService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final JwtAuthenticationService jwtAuthenticationService;

    public JwtAuthenticationFilter(JwtAuthenticationService jwtAuthenticationService) {
        this.jwtAuthenticationService = jwtAuthenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                System.out.println("<<<<<<<<<<     Start   >>>>>>>>>>");
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response); // Continue with the filter chain
            return; // Skip further processing in this filter
        }
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);  // Remove the "Bearer " prefix

            try {
                String username = jwtAuthenticationService.loadUserByToken(token).getUsername();

                if (username != null && jwtAuthenticationService.validateToken(token, username)) {
                    UserDetails userDetails = jwtAuthenticationService.loadUserByToken(token);
                    // Set the authentication context with the user details
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
                    );
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

        }

        filterChain.doFilter(request, response);  // Continue with the filter chain
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);  // Extract token after "Bearer "
        }
        return null;
    }
}
