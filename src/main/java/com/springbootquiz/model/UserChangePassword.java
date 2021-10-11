package com.springbootquiz.model;

import lombok.Data;

@Data
public class UserChangePassword {
    private String username;

    private String oldPassword;

    private String newPassword;
}
