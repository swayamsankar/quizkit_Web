package com.quizapp.quizapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔥 UNIQUE USERNAME
    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    private String role; // ADMIN / USER

    // 🔥 NEW FIELDS
    @Column(unique = true) // optional but recommended
    private String email;

    private String profileImage;

    // constructor
    public User() {}

    // getters & setters
    public Long getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getProfileImage() { return profileImage; }
    public void setProfileImage(String profileImage) { this.profileImage = profileImage; }
}