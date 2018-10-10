package com.epam.auction.command;

import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.Lot;
import com.epam.auction.model.User;
import com.epam.auction.model.dto.LotDTO;
import com.epam.auction.service.LotDTOService;
import com.epam.auction.service.LotService;
import com.epam.auction.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class LotInfoCommand implements Command {

    private static final String LOT_ID = "lotId";
    private static final String LOT_DTO = "lotDTO";
    private static final String LOT_INFO_PAGE = "/WEB-INF/lotInfo.jsp";
    private static final String BIDDERS_LIST = "biddersList";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String idString = request.getParameter(LOT_ID);
        long lotId = Long.valueOf(idString);
        LotDTOService lotDTOService = new LotDTOService();
        Optional<LotDTO> lotDTO = lotDTOService.findById(lotId);
        lotDTO.ifPresent(aDTOLot -> request.setAttribute(LOT_DTO, aDTOLot));

        UserService userService = new UserService();
        List<User> bidders = userService.findBidders(lotId);
        request.setAttribute(BIDDERS_LIST, bidders);

        return LOT_INFO_PAGE;
    }

}
