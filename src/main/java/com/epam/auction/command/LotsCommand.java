package com.epam.auction.command;

import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.Lot;
import com.epam.auction.model.dto.LotDTO;
import com.epam.auction.service.LotDTOService;
import com.epam.auction.service.LotService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LotsCommand implements Command {

    private static final String ID = "id";
    private static final String LOT_DTO_LIST = "lotDTOList";
    private static final String USER_LOTS_PAGE = "/WEB-INF/userLots.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        long id = (Long) session.getAttribute(ID);

        LotDTOService lotDTOService = new LotDTOService();
        List<LotDTO> lotDTOList = lotDTOService.findAllByUserId(id);
        request.setAttribute(LOT_DTO_LIST, lotDTOList);

        return USER_LOTS_PAGE;
    }
}
