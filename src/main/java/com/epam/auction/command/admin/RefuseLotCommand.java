package com.epam.auction.command.admin;

import com.epam.auction.command.Command;
import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.Lot;
import com.epam.auction.model.LotStatusEnum;
import com.epam.auction.model.dto.LotDTO;
import com.epam.auction.service.LotDTOService;
import com.epam.auction.service.LotService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RefuseLotCommand implements Command {

    private static final String LOT_MANAGEMENT_PAGE = "/WEB-INF/lotManagement.jsp";
    private static final String LOT_DTO_LIST = "lotDTOList";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException {
        String idLotString = request.getParameter("lotId");
        long idLot = Long.valueOf(idLotString);

        LotService lotService = new LotService();
        Optional<Lot> lot = lotService.findById(idLot);

        if (lot.isPresent()) {
            Lot lotItem = lot.get();
            lotItem.setStatus(LotStatusEnum.REFUSED);
            lotService.save(lotItem);
        }


        Map<String, String> parameters = new HashMap<>();
        parameters.put(Lot.STATUS, LotStatusEnum.PROCESSING.getValue());

        LotDTOService lotDTOService = new LotDTOService();
        List<LotDTO> lotDTOList = lotDTOService.findByParameters(parameters);
        request.setAttribute(LOT_DTO_LIST, lotDTOList);

        return LOT_MANAGEMENT_PAGE;
    }
}
