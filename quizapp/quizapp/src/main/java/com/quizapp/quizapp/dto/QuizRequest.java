package com.quizapp.quizapp.dto;

import java.util.Map;

public class QuizRequest {
    private Map<Long, String> answers;

    public Map<Long, String> getAnswers() { return answers; }
    public void setAnswers(Map<Long, String> answers) { this.answers = answers; }
}