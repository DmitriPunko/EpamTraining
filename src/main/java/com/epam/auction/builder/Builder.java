package com.epam.auction.builder;

import com.epam.auction.exception.DaoException;
import com.epam.auction.model.Identifiable;

import java.sql.ResultSet;
import java.util.Optional;

public interface Builder<T extends Identifiable> {
    T build(ResultSet resultSet) throws DaoException;
}
