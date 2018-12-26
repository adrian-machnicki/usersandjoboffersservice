package com.machnickiadrian.usersandjoboffersservice.user.exception;

import lombok.Getter;

public class UserLoginNotUniqueException extends RuntimeException {

    @Getter
    private String login;

    public UserLoginNotUniqueException(String login) {
        super("User with login " + login + " already exists");
        this.login = login;
    }
}
