package com.epam.auction.command;

import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.Lot;
import com.epam.auction.model.User;
import com.epam.auction.model.dto.LotDto;
import com.epam.auction.service.LotDtoService;
import com.epam.auction.service.LotService;
import com.epam.auction.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class BidCommand implements Command {

    private static final String LOT_DTO = "lotDto";
    private static final String LOT_INFO_PAGE = "/WEB-INF/lotInfo.jsp";
    private static final String BIDDERS_LIST = "biddersList";
    public static final String ID = "id";
    private static final String LOT_ID = "lotId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException {

        String lotIdString = request.getParameter(LOT_ID);
        long lotId =  Long.valueOf(lotIdString);

        LotService lotService = new LotService();
        Optional<Lot> lot = lotService.findById(lotId);

        HttpSession session = request.getSession();
        long userId = (Long) session.getAttribute(ID);
        UserService userService = new UserService();
        Optional<User> user = userService.findById(userId);

        if (lot.isPresent() && user.isPresent()) {
            Lot aLot = lot.get();
            lotService.bid(aLot);

            User aUser = user.get();
            userService.saveLotBidder(aUser, lotId);
        }

        LotDtoService lotDTOService = new LotDtoService();
        Optional<LotDto> lotDtoOptional = lotDTOService.findById(lotId);
        lotDtoOptional.ifPresent(aDTOLot -> request.setAttribute(LOT_DTO, aDTOLot));

        List<User> bidders = userService.findLotBidders(lotId);
        request.setAttribute(BIDDERS_LIST, bidders);

        return LOT_INFO_PAGE;
    }
}
