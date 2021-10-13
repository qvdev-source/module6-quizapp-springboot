package com.springbootquiz.controller;

import com.springbootquiz.model.User;
import com.springbootquiz.model.UserChangePassword;
import com.springbootquiz.repository.IUserRepository;
import com.springbootquiz.security.UserPrincipal;
import com.springbootquiz.service.IAuthenticationService;
import com.springbootquiz.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;


@RestController
@RequestMapping("api/authentication")//pre-path
public class AuthenticationController {
    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository userRepository;


    @Autowired
    private IUserService userService;

    @PostMapping("sign-up") //api/authentication/sign-up
    public ResponseEntity<?> signUp(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("sign-in")//api/authentication/sign-in
    public ResponseEntity<?> signIn(@RequestBody User user) {
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }

    @PostMapping("change-password")
    public ResponseEntity<?> changePass(@RequestBody UserChangePassword user){
        User user1 = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(()->new UsernameNotFoundException(user.getUsername()));
        if (passwordEncoder.matches(user.getOldPassword(),user1.getPassword())){
            user1.setPassword(passwordEncoder.encode(user.getNewPassword()));
            user1.setUpdateTime(LocalDateTime.now());
            userRepository.save(user1);
            return new ResponseEntity<>(user1,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
