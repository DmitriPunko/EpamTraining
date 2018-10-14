package com.epam.auction.dao;

import com.epam.auction.exception.DaoException;
import com.epam.auction.model.Lot;

import java.util.List;
import java.util.Map;

public interface LotDao {

    List<Lot> findAllByUserId(long id) throws DaoException;

    List<Lot> findAllActive() throws DaoException;

    List<Lot> findByParameters(Map<String, String> parameters) throws DaoException;

    void bid(Lot lot) throws DaoException;
}
