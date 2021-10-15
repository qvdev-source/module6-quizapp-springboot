package com.springbootquiz.repository;

import com.springbootquiz.model.QuizHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizHistoryRepository extends JpaRepository<QuizHistory,Long> {

    public List<QuizHistory> findAllByUserId(String userId);

}
