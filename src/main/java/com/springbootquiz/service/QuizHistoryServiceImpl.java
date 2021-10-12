package com.springbootquiz.service;

import com.springbootquiz.model.Quiz;
import com.springbootquiz.model.QuizHistory;
import com.springbootquiz.repository.QuizHistoryRepository;
import com.springbootquiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QuizHistoryServiceImpl implements QuizHistoryService {

    @Autowired
    private QuizHistoryRepository quizHistoryRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public QuizHistory saveQuizHistory(QuizHistory quizHistory) {
        quizHistory.setTimeCreate(LocalDateTime.now());
        return quizHistoryRepository.save(quizHistory);
    }
}
