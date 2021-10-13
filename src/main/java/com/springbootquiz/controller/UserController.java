package com.springbootquiz.controller;

import com.springbootquiz.model.User;
import com.springbootquiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/show-all")
    public ResponseEntity<List<User>> getAllUser()
    {
        return new ResponseEntity<>(userService.showAllUser(), HttpStatus.OK);
    }


}
