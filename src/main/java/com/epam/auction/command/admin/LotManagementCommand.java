package com.epam.auction.command.admin;

import com.epam.auction.command.Command;
import com.epam.auction.command.CommandResult;
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

/**
 * Designed to perform preparing and submitting the lot management page.
 */
public class LotManagementCommand implements Command {

    private static final String LOT_DTO_LIST = "lotDtoList";
    private static final String LOT_MANAGEMENT_PAGE = "/WEB-INF/lotManagement.jsp";

    /**
     * Process the request generates a result of processing in the form of
     * {@link com.epam.auction.command.CommandResult} object with lot management page.
     *
     * @param request  an {@link HttpServletRequest} object that contains client request
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @return A response in the form of {@link com.epam.auction.command.CommandResult} object.
     * @throws ServiceException when DaoException is caught.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Map<String, String> parameters = new HashMap<>();
        parameters.put(Lot.STATUS, LotStatusEnum.PROCESSING.getValue());

        LotDtoService lotDtoService = new LotDtoService();
        List<LotDto> lotDtoList = lotDtoService.findByParameters(parameters);
        request.setAttribute(LOT_DTO_LIST, lotDtoList);

        return new CommandResult(LOT_MANAGEMENT_PAGE, false);
    }
}
