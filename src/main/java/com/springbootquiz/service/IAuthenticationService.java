package com.springbootquiz.service;

import com.springbootquiz.model.User;


public interface IAuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}
