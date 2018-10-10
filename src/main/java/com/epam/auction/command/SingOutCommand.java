package com.epam.auction.command;

import com.epam.auction.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SingOutCommand implements Command{

    private static final String ID = "id";
    private static final String ROLE = "role";
    private static final String LOGIN_PAGE = "/WEB-INF/login.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        session.removeAttribute(ID);
        session.removeAttribute(ROLE);
        return LOGIN_PAGE;
    }

}
