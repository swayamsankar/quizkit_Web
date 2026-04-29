package com.quizapp.quizapp.dto;

public class QuizResponse {
    private int score;
    private int total;

    public QuizResponse(int score, int total) {
        this.score = score;
        this.total = total;
    }

    public int getScore() { return score; }
    public int getTotal() { return total; }
}