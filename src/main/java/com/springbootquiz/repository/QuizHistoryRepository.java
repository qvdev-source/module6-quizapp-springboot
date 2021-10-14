package com.springbootquiz.repository;

import com.springbootquiz.model.QuizHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizHistoryRepository extends JpaRepository<QuizHistory,Long> {
}
