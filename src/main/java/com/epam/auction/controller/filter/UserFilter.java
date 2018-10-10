package com.epam.auction.controller.filter;

import com.epam.auction.model.RoleEnum;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserFilter implements Filter {

    private static final String ROLE = "role";
    private static final String COMMAND = "command";
    private static final String LOGIN_COMMAND = "login";
    private static final String LOT_MANAGEMENT_COMMAND = "lotManagement";
    private static final String USER_MANAGEMENT_COMMAND = "userManagement";
    private static final String USER_LOTS_COMMAND = "userLots";

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String LOGIN_PAGE_PATH = "/WEB-INF/login.jsp";
    private static final String LOGIN_PAGE_STYLE_PATH = "/style/loginStyle.css";

    private static final String ERROR_ACCESS_MESSAGE = "errorAccessMessage";
    private static final String AUTHENTICATION_MESSAGE = "You should login to view this page";
    private static final String ACCESS_USER_MESSAGE = "You should login as admin to view this page";
    private static final String ACCESS_ADMIN_MESSAGE = "You should login as user to view this page";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String servletPath = request.getServletPath();

        checkForAuthorization(servletPath, request, response);
        checkForAccess(request, response);

        filterChain.doFilter(request, response);
    }

    private void checkForAccess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        RoleEnum role = (RoleEnum) session.getAttribute(ROLE);

        if (role != null) {
            String command = request.getParameter(COMMAND);

            if (role.equals(RoleEnum.USER) &&
                    (LOT_MANAGEMENT_COMMAND.equals(command) ||
                            USER_MANAGEMENT_COMMAND.equals(command))) {

                redirectToLoginWithMessage(request, response, ACCESS_USER_MESSAGE);
            }

            if (role.equals(RoleEnum.ADMIN) &&
                    USER_LOTS_COMMAND.equals(command)) {

                redirectToLoginWithMessage(request, response, ACCESS_ADMIN_MESSAGE);
            }
        }
    }

    private void checkForAuthorization(String servletPath, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String command = request.getParameter(COMMAND);

        if (!servletPath.endsWith(LOGIN_PAGE) &&
                !servletPath.endsWith(LOGIN_PAGE_STYLE_PATH) &&
                !LOGIN_COMMAND.equals(command)) {

            HttpSession session = request.getSession();
            RoleEnum role = (RoleEnum) session.getAttribute(ROLE);

            if (role == null) {
                redirectToLoginWithMessage(request, response, AUTHENTICATION_MESSAGE);
            }
        }
    }

    private void redirectToLoginWithMessage(HttpServletRequest request, HttpServletResponse response, String message) throws IOException, ServletException {
        request.setAttribute(ERROR_ACCESS_MESSAGE, message);
        ServletContext servletContext = request.getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher(LOGIN_PAGE_PATH);
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
    }
}
