package com.epam.auction.dao;

import com.epam.auction.exception.DaoException;
import com.epam.auction.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    List<User> findAllUsers() throws DaoException;

    List<User> findLotBidders(long lotId) throws DaoException;

    void saveLotBidder(User bidder, long lotId) throws DaoException;
}
