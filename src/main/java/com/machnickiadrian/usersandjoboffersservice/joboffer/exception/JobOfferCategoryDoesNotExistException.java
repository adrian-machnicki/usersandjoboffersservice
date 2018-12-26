package com.machnickiadrian.usersandjoboffersservice.joboffer.exception;

import lombok.Getter;

public class JobOfferCategoryDoesNotExistException extends RuntimeException {

    @Getter
    private String category;

    public JobOfferCategoryDoesNotExistException(String category) {
        super("Category " + category + " does not exists");
        this.category = category;
    }
}
