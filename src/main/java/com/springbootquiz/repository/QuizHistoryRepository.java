package com.springbootquiz.repository;

import com.springbootquiz.model.QuizHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface QuizHistoryRepository extends JpaRepository<QuizHistory,Long> {
public ArrayList<QuizHistory> findAllByUserId(String userId);
}
