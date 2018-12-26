package com.machnickiadrian.usersandjoboffersservice.joboffer.exception;

import lombok.Getter;

public class UserDoesNotExistException extends RuntimeException {

    @Getter
    private final String login;

    public UserDoesNotExistException(String login) {
        super("Cannot create offer. User with login=" + login + " does not exists");
        this.login = login;
    }
}
