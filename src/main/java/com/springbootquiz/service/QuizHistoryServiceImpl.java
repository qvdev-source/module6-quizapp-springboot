package com.springbootquiz.service;

import com.springbootquiz.model.Quiz;
import com.springbootquiz.model.QuizHistory;
import com.springbootquiz.model.User;
import com.springbootquiz.repository.IUserRepository;
import com.springbootquiz.repository.QuizHistoryRepository;
import com.springbootquiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class QuizHistoryServiceImpl implements QuizHistoryService {

    @Autowired
    private QuizHistoryRepository quizHistoryRepository;

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public ArrayList<QuizHistory> findByUserId(String userId) {
        return quizHistoryRepository.findAllByUserId(userId);
    }

    @Override
    public ArrayList<QuizHistory> finAll() {
        return (ArrayList<QuizHistory>) quizHistoryRepository.findAll();
    }

    @Override
    public QuizHistory saveQuizHistory(QuizHistory quizHistory) {

        Long userId= Long.valueOf((quizHistory.getUserId()));
        Long quizId= Long.valueOf(quizHistory.getQuizId());

        User user=iUserRepository.findById(userId).get();
        Quiz quiz=quizRepository.findById(quizId).get();

        quizHistory.setQuizTitle(quiz.getTitle());
        quizHistory.setUsername(user.getUsername());

        quizHistory.setTimeCreate(LocalDateTime.now());
        return quizHistoryRepository.save(quizHistory);
    }
}
