package com.quizapp.quizapp.controller;

import com.quizapp.quizapp.dto.QuestionDTO;
import com.quizapp.quizapp.dto.QuizRequest;
import com.quizapp.quizapp.dto.QuizResponse;
import com.quizapp.quizapp.model.Question;
import com.quizapp.quizapp.model.QuizResult;
import com.quizapp.quizapp.repository.QuestionRepository;
import com.quizapp.quizapp.repository.QuizResultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/questions")
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionController {

    @Autowired
    private QuestionRepository repo;

    @Autowired
    private QuizResultRepository resultRepo;

    // 🔥 Helper method
    private QuestionDTO convertToDTO(Question q) {
        return new QuestionDTO(
                q.getId(),
                q.getQuestion(),
                q.getOption1(),
                q.getOption2(),
                q.getOption3(),
                q.getOption4()
        );
    }

    // ✅ GET all questions (accessible to all authenticated users)
    @GetMapping
    public List<QuestionDTO> getAllQuestions() {
        return repo.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // 🔒 ADD question (ADMIN ONLY)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public QuestionDTO addQuestion(@RequestBody Question q) {
        Question saved = repo.save(q);
        return convertToDTO(saved);
    }

    // 🔒 DELETE question (ADMIN ONLY)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        repo.deleteById(id);
        return "Deleted successfully";
    }

    // 🔥 FILTER BY CATEGORY (all authenticated users)
    @GetMapping("/filter")
    public List<QuestionDTO> getByCategory(@RequestParam String category) {
        return repo.findByCategory(category)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // 🔥 RANDOM QUESTIONS (all authenticated users)
    @GetMapping("/random")
    public List<QuestionDTO> getRandomQuestions(@RequestParam int count) {
        return repo.findRandomQuestions(count)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // 🔒 SUBMIT QUIZ (USER ONLY)
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/submit")
    public QuizResponse submitQuiz(@RequestBody QuizRequest request,
                                   Authentication authentication) {

        int score = 0;
        int total = request.getAnswers().size();

        for (Long questionId : request.getAnswers().keySet()) {

            Question q = repo.findById(questionId).orElse(null);

            if (q != null) {
                String correct = q.getCorrectAnswer();
                String userAnswer = request.getAnswers().get(questionId);

                if (correct != null && correct.equalsIgnoreCase(userAnswer)) {
                    score++;
                }
            }
        }

        // 🔥 GET LOGGED-IN USER
        String username = authentication.getName();

        // 🔥 SAVE RESULT
        QuizResult result = new QuizResult(
                username,
                score,
                total,
                LocalDateTime.now()
        );

        resultRepo.save(result);

        return new QuizResponse(score, total);
    }

    // 🔒 VIEW RESULTS (USER + ADMIN)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/results")
    public List<QuizResult> getMyResults(Authentication authentication) {
        String username = authentication.getName();
        return resultRepo.findByUsername(username);
    }
}