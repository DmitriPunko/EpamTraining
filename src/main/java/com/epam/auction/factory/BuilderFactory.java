package com.epam.auction.factory;

import com.epam.auction.builder.Builder;
import com.epam.auction.builder.LotBuilder;
import com.epam.auction.builder.UserBuilder;

public class BuilderFactory {

    private static final String USER = "user";
    private static final String LOT = "lot";

    public static Builder create(String builderName) {

        switch (builderName) {
            case USER:
                return new UserBuilder();
            case LOT:
                return new LotBuilder();
            default:
                throw new IllegalArgumentException("Unknown Builder name!");
        }
    }

}
