package com.epam.auction.command;

import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.Lot;
import com.epam.auction.model.LotStatusEnum;
import com.epam.auction.model.User;
import com.epam.auction.model.dto.LotDto;
import com.epam.auction.service.LotDtoService;
import com.epam.auction.service.LotService;
import com.epam.auction.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class PayLotCommand implements Command {

    private static final String ID = "id";
    private static final String LOT_DTO_LIST = "lotDTOList";
    private static final String USER_LOTS_PAGE = "/WEB-INF/userLots.jsp";
    private static final String LOT_ID = "lotId";
    private static final String PAYMENT_MESSAGE = "paymentMessage";
    private static final String ERROR_PAYMENT_MESSAGE = "Not enough money to pay!";
    private static final String SUCCESS_PAYMENT_MESSAGE = "Successfully paid!";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException {

        String lotIdString = request.getParameter(LOT_ID);
        long lotId = Long.valueOf(lotIdString);

        LotService lotService = new LotService();
        Optional<Lot> lot = lotService.findById(lotId);

        HttpSession session = request.getSession();
        long userId = (Long) session.getAttribute(ID);

        UserService userService = new UserService();
        Optional<User> user = userService.findById(userId);

        if (lot.isPresent() && user.isPresent()) {
            Lot aLot = lot.get();
            User aUser = user.get();

            BigDecimal paymentAmount = aLot.getPrice();
            BigDecimal userBalance = aUser.getBalance();

            if (userBalance.compareTo(paymentAmount) >= 0) {
                BigDecimal residualBalance = userBalance.subtract(paymentAmount);
                aUser.setBalance(residualBalance);
                aLot.setStatus(LotStatusEnum.PAID);

                lotService.save(aLot);
                userService.save(aUser);

                request.setAttribute(PAYMENT_MESSAGE, SUCCESS_PAYMENT_MESSAGE);
            } else {
                request.setAttribute(PAYMENT_MESSAGE, ERROR_PAYMENT_MESSAGE);
            }
        }


        LotDtoService lotDTOService = new LotDtoService();
        List<LotDto> lotDtoList = lotDTOService.findAllByUserId(userId);
        request.setAttribute(LOT_DTO_LIST, lotDtoList);

        return USER_LOTS_PAGE;
    }
}
