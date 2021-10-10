package com.springbootquiz.service;

import com.springbootquiz.model.User;

import java.util.Optional;


public interface IUserService {
    User saveUser(User user);

    User changePassword(User user, String newPassword);

    Optional<User> findByUsername(String username);

    void makeAdmin(String username);

    void makeSuperAdmin(String username);
}
