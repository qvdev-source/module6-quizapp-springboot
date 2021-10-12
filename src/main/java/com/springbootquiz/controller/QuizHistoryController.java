package com.springbootquiz.controller;

import com.springbootquiz.model.QuizHistory;
import com.springbootquiz.service.QuizHistoryService;
import com.springbootquiz.service.QuizService;
import com.springbootquiz.service.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/save-history")
public class QuizHistoryController {

    @Autowired
    private QuizHistoryService quizHistory;

    @PostMapping
    public ResponseEntity<QuizHistory> addQuizHistory(@RequestBody QuizHistory quizHistory) throws Exception {
        return new ResponseEntity<>(this.quizHistory.saveQuizHistory(quizHistory), HttpStatus.OK);
    }

}
