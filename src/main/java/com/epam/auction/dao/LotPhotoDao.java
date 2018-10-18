package com.epam.auction.dao;

import com.epam.auction.exception.DaoException;
import com.epam.auction.model.LotPhoto;

import java.util.List;

/**
 *  Designed to determine the behavior of the implementing class in the form of database access objects
 *  of type {@link LotPhoto}.
 */
public interface LotPhotoDao {

    /**
     *  Method designed for searching lot photos depends on lot identifier.
     *
     * @param id - Lot identifier in database
     * @return an {@link List} implementation with an lot {@link LotPhoto} objects.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    List<LotPhoto> findLotPhotosByLotId(long id) throws DaoException;
}
