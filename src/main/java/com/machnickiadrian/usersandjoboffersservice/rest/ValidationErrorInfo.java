package com.machnickiadrian.usersandjoboffersservice.rest;

import lombok.Getter;
import org.springframework.validation.Errors;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class ValidationErrorInfo {

    @Getter
    private final String field;

    @Getter
    private final String message;

    private ValidationErrorInfo(String fieldName, String errorMessage) {
        this.message = errorMessage;
        this.field = fieldName;
    }

    public static Collection<ValidationErrorInfo> ofErrors(Errors errors) {
        return errors.getAllErrors().stream()
                .map(err -> new ValidationErrorInfo(err.getObjectName(), err.getDefaultMessage()))
                .collect(toList());
    }

}