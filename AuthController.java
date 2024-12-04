package com.example.rbac.rbac.controller;


import com.example.rbac.rbac.model.User;
import com.example.rbac.rbac.repository.UserRepository;
import com.example.rbac.rbac.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        UserRepository user = (UserRepository) userRepository.findByUsername(credentials.get("username"))
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(credentials.get("password"), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtils.generateToken(user.getUsername());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}



