package com.epam.auction.command;

import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.User;
import com.epam.auction.model.dto.LotDto;
import com.epam.auction.service.LotDtoService;
import com.epam.auction.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class LotInfoCommand implements Command {

    private static final String LOT_ID = "lotId";
    private static final String LOT_DTO = "lotDto";
    private static final String LOT_INFO_PAGE = "/WEB-INF/lotInfo.jsp";
    private static final String BIDDERS_LIST = "biddersList";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String idString = request.getParameter(LOT_ID);
        long lotId = Long.valueOf(idString);
        LotDtoService lotDTOService = new LotDtoService();
        Optional<LotDto> lotDTO = lotDTOService.findById(lotId);
        lotDTO.ifPresent(aDTOLot -> request.setAttribute(LOT_DTO, aDTOLot));

        UserService userService = new UserService();
        List<User> bidders = userService.findLotBidders(lotId);
        request.setAttribute(BIDDERS_LIST, bidders);

        return LOT_INFO_PAGE;
    }

}
