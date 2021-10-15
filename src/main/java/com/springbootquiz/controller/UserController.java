package com.springbootquiz.controller;

import com.springbootquiz.model.Email;
import com.springbootquiz.model.Quiz;
import com.springbootquiz.model.User;
import com.springbootquiz.repository.QuizRepository;
import com.springbootquiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.net.http.HttpResponse;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/show-all")
    public ResponseEntity<List<User>> getAllUser()
    {
        return new ResponseEntity<>(userService.showAllUser(), HttpStatus.OK);
    }

    @PostMapping ("/resetpassword")
    public ResponseEntity<?> resetPassword(@RequestBody Email email) throws MessagingException {
        userService.resetPassword(email.getEmail(), email.getUsername());
        return new ResponseEntity<>(email, OK);
    }

    @GetMapping("/getquiz")
    public ResponseEntity<?> findQuizActive(@RequestBody Quiz quiz)
    {
        return new ResponseEntity<>(quizRepository.findQuizzesByActiveTrue(), OK);
    }

    @GetMapping("/getquiznotactive")
    public ResponseEntity<?> findQuizNotActive(@RequestBody Quiz quiz)
    {
        return new ResponseEntity<>(quizRepository.findQuizzesByActiveFalse(), OK);
    }

}
