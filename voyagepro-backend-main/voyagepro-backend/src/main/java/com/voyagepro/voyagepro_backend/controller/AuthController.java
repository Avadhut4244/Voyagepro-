package com.voyagepro.voyagepro_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.voyagepro.voyagepro_backend.entity.User;
import com.voyagepro.voyagepro_backend.repository.UserRepository;
import com.voyagepro.voyagepro_backend.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // Register User
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userRepository.save(user);
        return "User registered successfully";
    }

    // Login User
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        // FIX: safe handling
        User existingUser = userRepository.findByUsername(user.getUsername()).orElse(null);

        if (existingUser == null) {
            return "User not found";
        }

        if (!existingUser.getPassword().equals(user.getPassword())) {
            return "Invalid password";
        }

        // Generate JWT token
        return jwtUtil.generateToken(existingUser.getUsername());
    }
}