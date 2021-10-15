package com.springbootquiz.service;

import com.springbootquiz.model.QuizHistory;

import java.util.ArrayList;
import java.util.Optional;

public interface QuizHistoryService {
    ArrayList<QuizHistory> findByUserId(String userId);

    ArrayList<QuizHistory> finAll();

    public QuizHistory saveQuizHistory(QuizHistory quizHistory) throws Exception;
}
