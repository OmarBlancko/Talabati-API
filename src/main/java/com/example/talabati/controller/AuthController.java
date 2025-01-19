package com.example.talabati.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.talabati.Exceptions.UserNotFoundException;
import com.example.talabati.config.Util.JwtUtil;
import com.example.talabati.model.ApiResponse;
import com.example.talabati.model.AuthRequest;
import com.example.talabati.model.AuthResponseDTO;
import com.example.talabati.model.User;
import com.example.talabati.service.UserService;

import jakarta.servlet.ServletException;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    // private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ApiResponse<User> signUp(@RequestBody User user) {

        ApiResponse<User> response = userService.createUser(user);
        User savedUser = response.getData();
        return response;
    }

    @PostMapping("/login")
    public <T> ApiResponse<AuthResponseDTO> login(@RequestBody AuthRequest authRequest) throws ServletException {
        try  {
            // logger.info("Login proccess sta", authRequest.getUsername());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword()
        );
        System.out.println("Auth state >>>>>>>>>>"+ authentication.getDetails());
        User existingUser = userService.findByUsername(authRequest.getUsername()).getData();
            if(existingUser == null) {
                throw new UserNotFoundException("Cannot find user with this credintials");
            }
            if (passwordEncoder.matches(authRequest.getPassword(),existingUser.getPassword())) {
                System.out.println("password matches  !!");
            }
            else {
                throw  new UserNotFoundException("Wrong info");
            }
            
        //  Authentication authenticatedUser = authenticationManager.authenticate(authentication);

            // logger.info("Authentication successful for user: {}", authRequest.getUsername());
           
            // SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // User user = (User) authentication.getPrincipal();
            // logger.info("Generating token for user: {}", user.getUsername());

             String token = jwtUtil.generateToken(existingUser.getUsername(), "USER");
             System.out.println(token);
            // logger.info("Login successful, token generated for user: {}", user.getUsername());

            // AuthResponseDTO responseDTO = new AuthResponseDTO(token,"Bearer");
            return new ApiResponse<>(200, "Login Succefully!", null);
        }
        catch(Exception e) {
            // logger.error("Login failed for user: {}", authRequest.getUsername(), e);

           return new  ApiResponse<>(400,"Invalid Username or password >>> error >> "+ e.getMessage(),null);
        }
       
    }

}
