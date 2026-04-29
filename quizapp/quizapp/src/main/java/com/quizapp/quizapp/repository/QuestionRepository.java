package com.quizapp.quizapp.repository;

import com.quizapp.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;        // ✅ ADD
import org.springframework.data.repository.query.Param;     // ✅ ADD

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    // 🔥 Filter by category
    List<Question> findByCategory(String category);

    // 🔥 Random questions
    @Query(value = "SELECT * FROM question ORDER BY RAND() LIMIT :count", nativeQuery = true)
    List<Question> findRandomQuestions(@Param("count") int count);
}