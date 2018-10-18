package com.epam.auction.dao;

import com.epam.auction.model.AuctionTypeEnum;
import com.epam.auction.model.Lot;
import com.epam.auction.model.LotStatusEnum;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DynamicQueryBuilderTest {

    private static final String EXPECTED_QUERY = "SELECT * FROM lot WHERE auction_type = ? AND status = ?";
    private static final Map<String, String> parameters = new HashMap<>();

    @BeforeClass
    public static void setParameters() {
        parameters.put(Lot.AUCTION_TYPE, AuctionTypeEnum.DIRECT.getValue());
        parameters.put(Lot.STATUS, LotStatusEnum.CONFIRMED.getValue());
    }

    @Test
    public void shouldBuildQueryByParameters() {
        String actualQuery = DynamicQueryBuilder.build(parameters);
        Assert.assertEquals(EXPECTED_QUERY, actualQuery);
    }
}