package com.epam.auction.command.admin;

import com.epam.auction.command.Command;
import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.Lot;
import com.epam.auction.model.LotStatusEnum;
import com.epam.auction.model.dto.LotDto;
import com.epam.auction.service.LotDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LotManagementCommand implements Command {

    private static final String LOT_DTO_LIST = "lotDtoList";
    private static final String LOT_MANAGEMENT_PAGE = "/WEB-INF/lotManagement.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Map<String, String> parameters = new HashMap<>();
        parameters.put(Lot.STATUS, LotStatusEnum.PROCESSING.getValue());

        LotDtoService lotDtoService = new LotDtoService();
        List<LotDto> lotDtoList = lotDtoService.findByParameters(parameters);
        request.setAttribute(LOT_DTO_LIST, lotDtoList);

        return LOT_MANAGEMENT_PAGE;
    }
}
