package com.springbootquiz.repository;

import com.springbootquiz.model.Question;
import com.springbootquiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Set<Question> findByQuiz(Quiz quiz);
}
