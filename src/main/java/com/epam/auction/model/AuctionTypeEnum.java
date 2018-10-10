package com.epam.auction.model;

public enum AuctionTypeEnum {
    DIRECT("direct"),
    REVERSE("reverse");

    private String value;
    private AuctionTypeEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
