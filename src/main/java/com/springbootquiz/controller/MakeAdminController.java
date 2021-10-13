package com.springbootquiz.controller;

import com.springbootquiz.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/makeadmin")
public class MakeAdminController {

    @Autowired
    private IUserService userService;

    @PutMapping("makeadmin/{username}") //api/makeadmin/makeadmin/{username} ->api make admin from superadmin
    public ResponseEntity<?> makeAdmin(@PathVariable String username) {
        userService.makeAdmin(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
