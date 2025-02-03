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
import com.example.talabati.service.JwtServices.JwtAuthenticationService;

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
                String authHeader = request.getHeader("Authorization");
        String token = getTokenFromRequest(request);
        // System.out.println("<<<<<<<<<<<<<<<<, token >>>>>>>> from jwt auth filter" +token);

        if (token != null ) {

            try {


                UserDetails userDetails = jwtAuthenticationService.loadUserByToken(token);
                System.out.println("Loaded User:  <>>>>>>>>>" + userDetails);

                if (jwtAuthenticationService.validateToken(token, userDetails.getUsername())) {
                    System.out.println("TOOOOOOOOOOOOOKKKKKKKKKKKKENNNNNNN VVVVVVVVVVALIED");

                    // Set the authentication context with the user details
                    UsernamePasswordAuthenticationToken authentication
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    // Set authentication in SecurityContextHolder
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                   
                } else {
                    System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOTTTT VALID");
                    throw new Exception("Invalid or expired JWT token");
                }
                
            } catch (Exception e) {
                logger.error("Authentication failed: " + e.getMessage());
                SecurityContextHolder.clearContext(); // Clear the SecurityContext in case of failure
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Token is invalid or expired");
            }

        }

        filterChain.doFilter(request, response);  // Continue with the filter chain
        // System.out.println("JWT Filter: Token Found - " + token);

    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            
            return header.substring(7);  // Extract token after "Bearer "
        }
        return null;
    }

}
