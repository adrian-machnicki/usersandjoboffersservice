package com.machnickiadrian.usersandjoboffersservice.user.exception;

import lombok.Getter;

public class UserNotFoundException extends RuntimeException {

    @Getter
    private Long userId;

    public UserNotFoundException(Long userId) {
        super("User with id " + userId + " does not exist");
        this.userId = userId;
    }
}
