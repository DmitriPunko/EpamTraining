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

public class MainCommand implements Command {

    private static final String LOT_DTO_LIST = "lotDTOList";
    private static final String MAIN_PAGE = "/WEB-INF/main.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        LotDTOService lotDTOService = new LotDTOService();
        List<LotDTO> lotDTOList = lotDTOService.findAllActive();
        request.setAttribute(LOT_DTO_LIST, lotDTOList);

        return MAIN_PAGE;

    }
}
