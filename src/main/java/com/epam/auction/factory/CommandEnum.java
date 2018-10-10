package com.epam.auction.factory;

public enum CommandEnum {
    LOGIN("login"),
    OFFERALOTPAGE("offerALotPage"),
    MAIN("main"),
    PROFILE("profile"),
    LOTINFO("lotInfo"),
    USERLOTS("userLots"),
    SIGNOUT("signOut"),
    LOTMANAGEMENT("lotManagement"),
    FINDLOTS("findLots"),
    OFFERALOT("offerALot"),
    USERMANAGEMENT("lotManagement"),
    CONFIRMLOT("confirmLot"),
    REFUSELOT("refuseLot"),
    BANUSER("banUser"),
    UNBANUSER("unbanUser");


    private String value;
    private CommandEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}