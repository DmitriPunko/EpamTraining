package com.epam.auction.command.admin;

import com.epam.auction.command.Command;
import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.User;
import com.epam.auction.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UserManagementCommand implements Command {

    private static final String USER_LIST = "userList";
    private static final String USER_MANAGEMENT_PAGE = "/WEB-INF/userManagement.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        UserService userService = new UserService();
        List<User> userList = userService.findAllUsers();
        request.setAttribute(USER_LIST, userList);

        return USER_MANAGEMENT_PAGE;
    }
}
