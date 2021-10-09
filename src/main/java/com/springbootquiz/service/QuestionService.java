package com.springbootquiz.service;

import com.springbootquiz.model.Question;
import com.springbootquiz.model.Quiz;

import java.util.Set;

public interface QuestionService {

    public Question addQuestion(Question question);

    public Question updateQuestion(Question question);

    public Set<Question> getQuestion();

    public Question getQuestion(Long questionId);

    public Set<Question> getQuestionOfQuiz(Quiz quiz);

    public void deleteQuestion(Long quesId);
}
