package com.quizapp.quizapp.controller;

import com.quizapp.quizapp.model.User;
import com.quizapp.quizapp.repository.UserRepository;
import com.quizapp.quizapp.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    // 🔥 REGISTER
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        if (repo.findByUsername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER"); // default role

        repo.save(user);

        return "User registered successfully";
    }

    // 🔥 LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User dbUser = repo.findByUsername(user.getUsername()).orElse(null);

        if (dbUser == null) {
            return "User not found";
        }

        if (!encoder.matches(user.getPassword(), dbUser.getPassword())) {
            return "Invalid username or password";
        }

        // 🔐 Generate JWT with role
        return JwtUtil.generateToken(
                dbUser.getUsername(),
                dbUser.getRole()
        );
    }

    // 🔑 FORGOT PASSWORD
    @PostMapping("/forgot")
    public String forgot(@RequestParam String username,
                         @RequestParam String newPassword) {

        User user = repo.findByUsername(username).orElse(null);

        if (user == null) return "User not found";

        user.setPassword(encoder.encode(newPassword));
        repo.save(user);

        return "Password updated successfully";
    }
}