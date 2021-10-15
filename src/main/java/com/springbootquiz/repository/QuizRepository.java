package com.springbootquiz.repository;

import com.springbootquiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findQuizzesByActiveTrue();
    List<Quiz> findQuizzesByActiveFalse();
}
