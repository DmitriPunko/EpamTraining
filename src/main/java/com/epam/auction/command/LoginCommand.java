package com.epam.auction.command;

import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.RoleEnum;
import com.epam.auction.model.User;
import com.epam.auction.model.dto.LotDTO;
import com.epam.auction.service.LotDTOService;
import com.epam.auction.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class LoginCommand implements Command {
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ID = "id";
    private static final String ROLE = "role";
    private static final String LOT_DTO_LIST = "lotDTOList";
    private static final String MAIN_PAGE = "/WEB-INF/main.jsp";
    private static final String LOGIN_PAGE = "/WEB-INF/login.jsp";
    private static final String ERROR_LOGIN_MESSAGE = "errorLoginMessage";
    private static final String AUTHENTICATION_FAILED_MESSAGE = "Authentication failed!";
    private static final String ACCOUNT_HAS_BEEN_BANNED_MESSAGE = "Your account has been banned!";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        UserService userService = new UserService();
        String login = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        Optional<User> user = userService.login(login, password);

        if (!user.isPresent()) {
            request.setAttribute(ERROR_LOGIN_MESSAGE, AUTHENTICATION_FAILED_MESSAGE);
            return LOGIN_PAGE;
        }

        User userEntity = user.get();

        if (userEntity.isBanned()) {
            request.setAttribute(ERROR_LOGIN_MESSAGE, ACCOUNT_HAS_BEEN_BANNED_MESSAGE);
            return LOGIN_PAGE;
        }

        long id = userEntity.getId();
        RoleEnum role = userEntity.getRole();

        HttpSession session = request.getSession();
        session.setAttribute(ID, id);
        session.setAttribute(ROLE, role);

        LotDTOService lotDTOService = new LotDTOService();
        List<LotDTO> lotDTOList = lotDTOService.findAllActive();
        request.setAttribute(LOT_DTO_LIST, lotDTOList);

        return MAIN_PAGE;
    }

}
