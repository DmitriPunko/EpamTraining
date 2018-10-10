package com.epam.auction.dao;

import com.epam.auction.exception.DaoException;
import com.epam.auction.model.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
}
