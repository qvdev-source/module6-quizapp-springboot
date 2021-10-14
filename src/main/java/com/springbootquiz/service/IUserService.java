package com.springbootquiz.service;

import com.springbootquiz.model.Category;
import com.springbootquiz.model.User;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;


public interface IUserService {
    User saveUser(User user);

    User changePassword(User user, String newPassword);

    Optional<User> findByUsername(String username);

    List<User> showAllUser();

    void makeAdmin(String username);

    void makeSuperAdmin(String username);

<<<<<<< HEAD
    public User getUser(Long userId);
=======
    void makeUser(String username);

    void resetPassword(String email, String username) throws MessagingException;
>>>>>>> dev
}
