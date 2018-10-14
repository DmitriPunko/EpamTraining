package com.epam.auction.service;

import com.epam.auction.dao.creator.DaoCreator;
import com.epam.auction.dao.impl.LotDaoImpl;
import com.epam.auction.exception.DaoException;
import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.Lot;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LotService {

    public List<Lot> findAllActive() throws ServiceException {

        try (DaoCreator daoCreator = new DaoCreator()) {
            LotDaoImpl lotDao = daoCreator.getLotDaoImpl();

            return lotDao.findAllActive();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Lot> findById(long id) throws ServiceException {

        try (DaoCreator daoCreator = new DaoCreator()) {
            LotDaoImpl lotDao = daoCreator.getLotDaoImpl();

            return lotDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Lot> findAllByUserId(long id) throws ServiceException {
        try (DaoCreator daoCreator = new DaoCreator()) {
            LotDaoImpl lotDao = daoCreator.getLotDaoImpl();

            return lotDao.findAllByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Lot> findByParameters(Map<String, String> parameters) throws ServiceException {
        try (DaoCreator daoCreator = new DaoCreator()) {
            LotDaoImpl lotDao = daoCreator.getLotDaoImpl();

            return lotDao.findByParameters(parameters);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public long save(Lot item) throws ServiceException {
        try (DaoCreator daoCreator = new DaoCreator()) {
            LotDaoImpl lotDao = daoCreator.getLotDaoImpl();

            return lotDao.save(item);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void bid(Lot lot) throws ServiceException {
        try (DaoCreator daoCreator = new DaoCreator()) {
            LotDaoImpl lotDao = daoCreator.getLotDaoImpl();
            lotDao.bid(lot);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
