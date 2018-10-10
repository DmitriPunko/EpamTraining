package com.epam.auction.model;

public enum LotStatusEnum {
    PROCESSING("processing"),
    CONFIRMED("confirmed"),
    REFUSED("refused"),
    PAYMENTWAITING("payment-waiting"),
    PAID("paid");

    private String value;
    private LotStatusEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
