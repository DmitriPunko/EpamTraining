package com.epam.auction.command;

import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.Lot;
import com.epam.auction.model.LotStatusEnum;
import com.epam.auction.model.dto.LotDto;
import com.epam.auction.service.LotDtoService;
import com.epam.auction.util.DateTimeParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindLotsCommand implements Command {

    private static final String MAIN_PAGE = "/WEB-INF/main.jsp";
    private static final String LOT_DTO_LIST = "lotDtoList";

    private static final String YEAR_FROM = "year_of_issue_from";
    private static final String YEAR_TO = "year_of_issue_to";
    private static final String DATE_OF_START_FROM = "date_of_start_from";
    private static final String DATE_OF_END_TO = "date_of_end_to";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Map<String, String> parameters = findLotsParameters(request);

        LotDtoService lotDtoService = new LotDtoService();
        List<LotDto> lotDtoList = lotDtoService.findByParameters(parameters);

        request.setAttribute(LOT_DTO_LIST, lotDtoList);

        return MAIN_PAGE;
    }

    private Map<String, String> findLotsParameters(HttpServletRequest request) {

        String auctionType = request.getParameter(Lot.AUCTION_TYPE);
        String brand = request.getParameter(Lot.BRAND);
        String yearFrom = request.getParameter(YEAR_FROM);
        String yearTo = request.getParameter(YEAR_TO);
        String isDamaged = request.getParameter(Lot.IS_DAMAGED);

        Map<String, String> parameters = new HashMap<>();
        parameters.put(Lot.AUCTION_TYPE, auctionType);
        parameters.put(Lot.BRAND, brand);
        parameters.put(YEAR_FROM, yearFrom);
        parameters.put(YEAR_TO, yearTo);
        parameters.put(Lot.IS_DAMAGED, isDamaged);
        parameters.put(Lot.STATUS, LotStatusEnum.CONFIRMED.getValue());

        Date currentDate = new Date();
        String currentDateString = DateTimeParser.parse(currentDate);
        parameters.put(DATE_OF_START_FROM, currentDateString);
        parameters.put(DATE_OF_END_TO, currentDateString);


        return parameters;
    }
}
