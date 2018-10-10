package com.epam.auction.dao;

import com.epam.auction.exception.DaoException;
import com.epam.auction.model.LotPhoto;

import java.util.List;

public interface LotPhotoDao {
    List<LotPhoto> findLotPhotosByLotId(long id) throws DaoException;
}
