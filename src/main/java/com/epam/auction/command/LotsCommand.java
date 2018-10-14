package com.epam.auction.command;

import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.dto.LotDto;
import com.epam.auction.service.LotDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LotsCommand implements Command {

    private static final String ID = "id";
    private static final String LOT_DTO_LIST = "lotDtoList";
    private static final String USER_LOTS_PAGE = "/WEB-INF/userLots.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        long id = (Long) session.getAttribute(ID);

        LotDtoService lotDtoService = new LotDtoService();
        List<LotDto> lotDtoList = lotDtoService.findAllByUserId(id);
        request.setAttribute(LOT_DTO_LIST, lotDtoList);

        return USER_LOTS_PAGE;
    }
}
