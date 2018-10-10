package com.epam.auction.model;

import java.math.BigDecimal;
import java.util.List;

public class Auction {
    private Lot lot;
    private BigDecimal currentBid;
    private List<Long> participants;
}
