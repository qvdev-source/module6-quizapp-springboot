package com.springbootquiz.service;

import com.springbootquiz.model.User;

import java.util.Optional;


public interface IUserService {
    User saveUser(User user);

    Optional<User> findByUsername(String username);

    void makeAdmin(String username);

    void makeSuperAdmin(String username);
}
