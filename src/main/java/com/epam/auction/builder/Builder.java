package com.epam.auction.builder;

import com.epam.auction.exception.DaoException;
import com.epam.auction.model.Identifiable;

import java.sql.ResultSet;

/**
 *  Designed to determine the behavior of the implementing class in the form of construction
 *  an object of type T with specified characteristics.
 *
 * @param <T> - type of object.
 */
public interface Builder<T extends Identifiable> {
    /**
     *  Builds an object of type T with properties.
     *
     * @param resultSet Instance of {@link java.sql.ResultSet} with property set to build an object type T.
     * @return Returns built object type T.
     * @throws DaoException Throws when SQL Exception is caught.
     */
    T build(ResultSet resultSet) throws DaoException;
}
