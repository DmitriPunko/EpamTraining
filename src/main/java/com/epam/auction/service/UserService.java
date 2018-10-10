package com.epam.auction.service;

import com.epam.auction.dao.creator.DaoCreator;
import com.epam.auction.dao.impl.UserDaoImpl;
import com.epam.auction.exception.DaoException;
import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.User;

import java.util.List;
import java.util.Optional;

public class UserService {

    public Optional<User> login(String login, String password) throws ServiceException {

        try (DaoCreator daoCreator = new DaoCreator()) {
            UserDaoImpl userDao = daoCreator.getUserDaoImpl();
            return userDao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<User> findById(long id) throws ServiceException {

        try (DaoCreator daoCreator = new DaoCreator()) {
            UserDaoImpl userDao = daoCreator.getUserDaoImpl();
            return userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<User> findAllUsers() throws ServiceException {

        try (DaoCreator daoCreator = new DaoCreator()) {
            UserDaoImpl userDao = daoCreator.getUserDaoImpl();
            return userDao.findAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public long save(User item) throws ServiceException {
        try (DaoCreator daoCreator = new DaoCreator()) {
            UserDaoImpl userDao = daoCreator.getUserDaoImpl();

            return userDao.save(item);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<User> findBidders(long lotId) throws ServiceException {
        try (DaoCreator daoCreator = new DaoCreator()) {
            UserDaoImpl userDao = daoCreator.getUserDaoImpl();

            return userDao.findBidders(lotId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
