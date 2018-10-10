package com.epam.auction.dao;

import com.epam.auction.exception.DaoException;
import com.epam.auction.model.Identifiable;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {
    Optional<T> findById(Long id) throws DaoException;

    List<T> findAll() throws DaoException;

    long save(T item) throws DaoException;

    void removeById(Long id) throws DaoException;

}
