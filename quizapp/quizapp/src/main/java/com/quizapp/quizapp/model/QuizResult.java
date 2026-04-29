package com.quizapp.quizapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; // simple for now

    private int score;
    private int total;

    private LocalDateTime submittedAt;

    public QuizResult() {}

    public QuizResult(String username, int score, int total, LocalDateTime submittedAt) {
        this.username = username;
        this.score = score;
        this.total = total;
        this.submittedAt = submittedAt;
    }

    // getters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public int getScore() { return score; }
    public int getTotal() { return total; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
}