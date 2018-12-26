package com.machnickiadrian.usersandjoboffersservice.joboffer;

public enum JobOfferCategory {

    IT("IT"),
    FOOD_AND_DRINKS("Food & Drinks"),
    OFFICE("Office"),
    COURIER("Courier"),
    SHOP_ASSISTANT("Shop assistant");

    final private String name;

    JobOfferCategory(String name) {
        this.name = name;
    }
}
