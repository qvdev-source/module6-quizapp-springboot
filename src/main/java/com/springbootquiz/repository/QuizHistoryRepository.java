package com.springbootquiz.repository;

import com.springbootquiz.model.QuizHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizHistoryRepository extends JpaRepository<QuizHistory,Long> {
    Optional<QuizHistory> findByUserName(String userId);

}
