package com.epam.auction.command;

import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.dto.LotDto;
import com.epam.auction.service.LotDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MainCommand implements Command {

    private static final String LOT_DTO_LIST = "lotDtoList";
    private static final String MAIN_PAGE = "/WEB-INF/main.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        LotDtoService lotDtoService = new LotDtoService();
        List<LotDto> lotDtoList = lotDtoService.findAllActive();
        request.setAttribute(LOT_DTO_LIST, lotDtoList);

        return MAIN_PAGE;

    }
}
