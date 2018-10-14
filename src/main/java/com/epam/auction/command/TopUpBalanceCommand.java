package com.epam.auction.command;

import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.RoleEnum;
import com.epam.auction.model.User;
import com.epam.auction.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

public class TopUpBalanceCommand implements Command {

    private static final String ID = "id";
    private static final String USER = "user";
    private static final String ROLE = "role";
    private static final String ADMIN_PROFILE_PAGE = "/WEB-INF/adminProfile.jsp";
    private static final String USER_PROFILE_PAGE = "/WEB-INF/userProfile.jsp";
    private static final String TOP_UP_BALANCE = "topUpBalance";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException {

        HttpSession session = request.getSession();
        long id = (Long) session.getAttribute(ID);
        RoleEnum role = (RoleEnum) session.getAttribute(ROLE);

        UserService userService = new UserService();
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            String topUpBalanceString = request.getParameter(TOP_UP_BALANCE);
            BigDecimal topUpBalance = new BigDecimal(topUpBalanceString);

            User aUser = user.get();
            BigDecimal balance = aUser.getBalance();
            BigDecimal newBalance = balance.add(topUpBalance);

            aUser.setBalance(newBalance);

            userService.save(aUser);
            request.setAttribute(USER, aUser);
        }

        return RoleEnum.ADMIN.equals(role) ? ADMIN_PROFILE_PAGE : USER_PROFILE_PAGE;
    }
}
