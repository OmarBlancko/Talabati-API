package com.example.talabati.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.talabati.config.Util.JwtUtil;
import com.example.talabati.model.ApiResponse;
import com.example.talabati.model.AuthRequest;
import com.example.talabati.model.AuthResponseDTO;
import com.example.talabati.model.User;
import com.example.talabati.service.UserServices.UserService;

import jakarta.servlet.ServletException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    // private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ApiResponse<User> signUp(@RequestBody User user) {

        ApiResponse<User> response = userService.createUser(user);
        return response;
    }

    @PostMapping("/login")
    public <T> ApiResponse<AuthResponseDTO> login(@RequestBody AuthRequest authRequest) throws ServletException {
        try {

            Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());

            String username = authentication.getPrincipal().toString();

            // Fetch your User object from the database
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            String token = jwtUtil.generateToken(username, "USER");
            System.out.println("Auth Tokennnnnnnnnnnnn >>>>>>>>>>>>>>> "+ token);
            

            AuthResponseDTO responseDTO = new AuthResponseDTO(token, "Bearer");
            return new ApiResponse<>(200, "Login Succefully!", responseDTO);

        } catch (Exception e) {
            // logger.error("Login failed for user: {}", authRequest.getUsername(), e);

            return new ApiResponse<>(400, "Invalid Username or password >>> error >> " + e.getMessage(), null);
        }

    }
@GetMapping("/check-authentication")
    public String checkAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated()) {
            return "You are authenticated! User: " + authentication.getName();
        } else {
            return "You are not authenticated!";
        }
    }
}
