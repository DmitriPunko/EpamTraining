package com.epam.auction.model;

public enum ColorEnum {
    RED("Red"),
    ORANGE("Orange"),
    YELLOW("Yellow"),
    GREEN("Green"),
    BLUE("Blue"),
    GREY("Grey"),
    PURPLE("Purple"),
    WHITE("White"),
    BLACK("Black");

    private String value;
    private ColorEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
