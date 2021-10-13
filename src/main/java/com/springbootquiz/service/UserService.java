package com.springbootquiz.service;

import com.springbootquiz.model.Role;
import com.springbootquiz.model.User;
import com.springbootquiz.repository.IUserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setCreateTime(LocalDateTime.now());

        return userRepository.save(user);
    }


    @Override
    public User changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdateTime(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> showAllUser() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void makeAdmin(String username) {
        userRepository.updateUserRole(username, Role.ADMIN);
    }

    @Override
    @Transactional
    public void makeUser(String username) {
        userRepository.updateUserRole(username, Role.USER);
    }

    @Override
    @Transactional
    public void makeSuperAdmin(String username) {
        userRepository.updateUserRole(username, Role.SUPER_ADMIN);
    }

    @Override
    public void resetPassword(String email,String username) throws MessagingException {

        User user = userRepository.findByUsername(username).get();
        if (user != null){
            String password = generatePassword();
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            emailService.sendNewPasswordEmail(username, password, email);
        }

    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

}
