package com.epam.auction.service;

import com.epam.auction.dao.creator.DaoCreator;
import com.epam.auction.dao.impl.LotPhotoDaoImpl;
import com.epam.auction.exception.DaoException;
import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.LotPhoto;

import java.util.List;

public class LotPhotoService {

    public List<LotPhoto> getPhotoByLotId(long id) throws ServiceException {
        try (DaoCreator daoCreator = new DaoCreator()) {
            LotPhotoDaoImpl lotPhotoDao = daoCreator.getLotPhotoDaoImpl();

            return lotPhotoDao.findLotPhotosByLotId(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public long saveLotPhotos(LotPhoto lotPhoto) throws ServiceException {
        try (DaoCreator daoCreator = new DaoCreator()) {
            LotPhotoDaoImpl lotPhotoDao = daoCreator.getLotPhotoDaoImpl();

            return lotPhotoDao.save(lotPhoto);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
