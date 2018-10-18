package com.epam.auction.command.admin;

import com.epam.auction.command.Command;
import com.epam.auction.command.CommandResult;
import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.User;
import com.epam.auction.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Designed to perform preparing and submitting the user management page.
 */
public class UserManagementCommand implements Command {

    private static final String USER_LIST = "userList";
    private static final String USER_MANAGEMENT_PAGE = "/WEB-INF/userManagement.jsp";

    /**
     * Process the request and generates a result of processing in the form of
     * {@link com.epam.auction.command.CommandResult} object with user management page.
     *
     * @param request  an {@link HttpServletRequest} object that contains client request
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @return A response in the form of {@link com.epam.auction.command.CommandResult} object.
     * @throws ServiceException when DaoException is caught.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        UserService userService = new UserService();
        List<User> userList = userService.findAllUsers();
        request.setAttribute(USER_LIST, userList);

        return new CommandResult(USER_MANAGEMENT_PAGE, false);
    }
}
