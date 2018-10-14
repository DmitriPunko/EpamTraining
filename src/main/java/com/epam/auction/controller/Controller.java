package com.epam.auction.controller;

import com.epam.auction.command.Command;
import com.epam.auction.exception.ServiceException;
import com.epam.auction.factory.CommandFactory;
import com.epam.auction.model.BiddingCloser;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
    private static final String COMMAND = "command";
    private static final String ERROR_PAGE = "/WEB-INF/error.jsp";

    @Override
    public void init() throws ServletException {
        BiddingCloser biddingCloser = new BiddingCloser();
        biddingCloser.start();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String command = request.getParameter(COMMAND);
        Command action = CommandFactory.create(command);

        String page;
        try {
            page = action.execute(request, response);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            page = ERROR_PAGE;
        }

        dispatch(request, response, page);
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }
}
