package com.machnickiadrian.usersandjoboffersservice.rest;

import com.machnickiadrian.usersandjoboffersservice.joboffer.exception.JobOfferCategoryDoesNotExistException;
import com.machnickiadrian.usersandjoboffersservice.joboffer.exception.UserDoesNotExistException;
import com.machnickiadrian.usersandjoboffersservice.user.exception.UserLoginNotUniqueException;
import com.machnickiadrian.usersandjoboffersservice.user.exception.UserNotFoundException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({
            UserNotFoundException.class
    })
    ResponseEntity<ErrorInfo> notFound(RuntimeException e) {
        return new ResponseEntity<>(new ErrorInfo(e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            UserDoesNotExistException.class,
            JobOfferCategoryDoesNotExistException.class,
            UserLoginNotUniqueException.class
    })
    ResponseEntity<ErrorInfo> badRequest(RuntimeException e) {
        return new ResponseEntity<>(new ErrorInfo(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<ErrorInfo> emptyBody(RuntimeException e) {
        return new ResponseEntity<>(new ErrorInfo("Required request body is missing"), HttpStatus.BAD_REQUEST);
    }

    private static class ErrorInfo {
        @Getter
        private final String message;

        private ErrorInfo(RuntimeException e) {
            this.message = e.getMessage();
        }

        private ErrorInfo(String message) {
            this.message = message;
        }
    }

}
