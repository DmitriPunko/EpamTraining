package com.epam.auction.command;

import com.epam.auction.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OfferALotPageCommand implements Command {

    public static final String OFFER_A_LOT_PAGE = "/WEB-INF/offerALot.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return OFFER_A_LOT_PAGE;
    }
}
