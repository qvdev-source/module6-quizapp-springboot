package com.springbootquiz.service;

import com.springbootquiz.model.Category;
import com.springbootquiz.model.Quiz;

import java.util.ArrayList;
import java.util.Set;

public interface QuizService {

    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public Set<Quiz> getQuizzes();

    public Quiz getQuiz(Long quizId);

    public void deleteQuiz(Long quizId);

    public ArrayList<Quiz> findAllByTitle(String name);
}
