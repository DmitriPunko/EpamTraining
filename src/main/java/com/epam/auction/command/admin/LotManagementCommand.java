package com.epam.auction.command.admin;

import com.epam.auction.command.Command;
import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.Lot;
import com.epam.auction.model.LotStatusEnum;
import com.epam.auction.model.RoleEnum;
import com.epam.auction.model.dto.LotDTO;
import com.epam.auction.service.LotDTOService;
import com.epam.auction.service.LotService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LotManagementCommand implements Command {

    private static final String LOT_DTO_LIST = "lotDTOList";
    private static final String LOT_MANAGEMENT_PAGE = "/WEB-INF/lotManagement.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Map<String, String> parameters = new HashMap<>();
        parameters.put(Lot.STATUS, LotStatusEnum.PROCESSING.getValue());

        LotDTOService lotDTOService = new LotDTOService();
        List<LotDTO> lotDTOList = lotDTOService.findByParameters(parameters);
        request.setAttribute(LOT_DTO_LIST, lotDTOList);

        return LOT_MANAGEMENT_PAGE;
    }
}
