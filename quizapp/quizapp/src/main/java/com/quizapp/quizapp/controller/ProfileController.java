package com.quizapp.quizapp.controller;

import com.quizapp.quizapp.model.User;
import com.quizapp.quizapp.repository.UserRepository;
import com.quizapp.quizapp.dto.UpdateProfileDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

// 🔥 ADD THIS
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfileController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    // 🔒 GET profile (USER + ADMIN)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public User getProfile(Authentication auth) {
        return repo.findByUsername(auth.getName()).orElse(null);
    }

    // 🔒 UPDATE profile (USER + ADMIN)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping
    public String updateProfile(@RequestBody UpdateProfileDTO dto,
                                Authentication auth) {

        User user = repo.findByUsername(auth.getName()).orElse(null);

        if (user == null) return "User not found";

        if (dto.username != null && !dto.username.isEmpty()) {
            user.setUsername(dto.username);
        }

        if (dto.email != null && !dto.email.isEmpty()) {
            user.setEmail(dto.email);
        }

        if (dto.password != null && !dto.password.isEmpty()) {
            user.setPassword(encoder.encode(dto.password));
        }

        repo.save(user);
        return "Profile updated";
    }
}