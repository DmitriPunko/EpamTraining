package com.epam.auction.service;

import com.epam.auction.dao.creator.DaoCreator;
import com.epam.auction.dao.impl.UserDaoImpl;
import com.epam.auction.exception.DaoException;
import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.User;

import java.util.List;

public class BidderService {
    public List<User> findBidders(long lotId) throws ServiceException {
        try (DaoCreator daoCreator = new DaoCreator()) {
            UserDaoImpl userDao = daoCreator.getUserDaoImpl();

            return userDao.findLotBidders(lotId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
