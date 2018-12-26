package com.machnickiadrian.usersandjoboffersservice.rest;

import com.machnickiadrian.usersandjoboffersservice.user.exception.UserLoginNotUniqueException;
import com.machnickiadrian.usersandjoboffersservice.user.exception.UserNotFoundException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({UserNotFoundException.class, UserLoginNotUniqueException.class})
    ResponseEntity<ErrorInfo> userNotFoundException(RuntimeException e) {
        return new ResponseEntity<>(new ErrorInfo(e), HttpStatus.NOT_FOUND);
    }

    private static class ErrorInfo {
        @Getter
        private final String message;

        private ErrorInfo(RuntimeException e) {
            this.message = e.getMessage();
        }
    }

}
