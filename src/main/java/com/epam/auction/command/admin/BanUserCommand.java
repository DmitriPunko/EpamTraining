package com.epam.auction.command.admin;

import com.epam.auction.command.Command;
import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.User;
import com.epam.auction.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class BanUserCommand implements Command {

    private static final String USER_LIST = "userList";
    private static final String USER_MANAGEMENT_PAGE = "/WEB-INF/userManagement.jsp";
    private static final String USER_ID = "userId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException {

        String userIdString = request.getParameter(USER_ID);
        long userId = Long.valueOf(userIdString);

        UserService userService = new UserService();
        Optional<User> user = userService.findById(userId);

        if (user.isPresent()) {
            User userItem = user.get();
            userItem.setBanned(true);
            userService.save(userItem);
        }

        List<User> userList = userService.findAllUsers();
        request.setAttribute(USER_LIST, userList);

        return USER_MANAGEMENT_PAGE;
    }
}
