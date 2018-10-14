package com.epam.auction.command;

import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.RoleEnum;
import com.epam.auction.model.dto.LotDto;
import com.epam.auction.service.LotDtoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LanguageCommand implements Command {

    private static final String LANGUAGE = "language";
    private static final String LOGIN_PAGE = "/WEB-INF/login.jsp";
    private static final String MAIN_PAGE = "/WEB-INF/main.jsp";
    private static final String LOT_DTO_LIST = "lotDtoList";
    private static final String ROLE = "role";
    private static final String RU = "RU";
    private static final String EN = "EN";
    private static final String UNKNOWN_LANGUAGE_MESSAGE = "Unknown language!";
    private static final String NEXT_LANGUAGE = "nextLanguage";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException {
        String language = request.getParameter(LANGUAGE);
        HttpSession session = request.getSession();
        session.setAttribute(LANGUAGE, language);

        String nextLanguage = findNextLanguage(language);
        request.setAttribute(NEXT_LANGUAGE, nextLanguage);
        session.setAttribute(NEXT_LANGUAGE, nextLanguage);

        RoleEnum userRole = (RoleEnum) session.getAttribute(ROLE);
        String page;
        if (userRole != null) {
            page = MAIN_PAGE;
            LotDtoService lotDtoService = new LotDtoService();
            List<LotDto> lotDtoList = lotDtoService.findAllActive();
            request.setAttribute(LOT_DTO_LIST, lotDtoList);
        } else {
            page = LOGIN_PAGE;
        }
        return page;
    }

    private String findNextLanguage(String language) {
        String nextLanguage;
        switch (language) {
            case RU:
                nextLanguage = EN;
                break;
            case EN:
                nextLanguage = RU;
                break;
            default:
                throw  new IllegalArgumentException(UNKNOWN_LANGUAGE_MESSAGE);
        }
        return nextLanguage;
    }
}
