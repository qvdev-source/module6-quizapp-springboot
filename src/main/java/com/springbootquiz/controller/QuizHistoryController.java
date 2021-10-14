package com.springbootquiz.controller;

import com.springbootquiz.model.QuizHistory;
import com.springbootquiz.service.QuizHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/save-history")
public class QuizHistoryController {

    @Autowired
    private QuizHistoryService quizHistory;

    @PostMapping
    public ResponseEntity<QuizHistory> addQuizHistory(@RequestBody QuizHistory quizHistory) throws Exception {
        return new ResponseEntity<>(this.quizHistory.saveQuizHistory(quizHistory), HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<QuizHistory>findById(@PathVariable String userId) throws Exception{
        return new ResponseEntity<>(this.quizHistory.findByUserName(userId).get(),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<ArrayList<QuizHistory>> findAll() throws Exception{
        return new ResponseEntity<>(this.quizHistory.finAll(),HttpStatus.OK);
    }

}
