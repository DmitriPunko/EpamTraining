package com.epam.auction.dao;

import com.epam.auction.exception.DaoException;
import com.epam.auction.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Designed to determine the behavior of the implementing class in the form of database access objects
 * of type {@link User}.
 */
public interface UserDao {

    /**
     * Method designed for searching user depends on user login and password.
     *
     * @param login    is a {@link String} object that contains user login
     * @param password is a {@link String} object that contains user password
     * @return a {@link Optional} object with finding {@link User} inside.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    /**
     * Method designed for searching users.
     *
     * @return a {@link List} implementation with a {@link User} objects.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    List<User> findAllUsers() throws DaoException;

    /**
     * Method designed for searching {@link com.epam.auction.model.Lot} bidders
     * depends on {@link com.epam.auction.model.Lot} identifier.
     *
     * @param lotId is an identifier of {@link com.epam.auction.model.Lot}
     * @return a {@link List} implementation with a {@link User} objects.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    List<User> findLotBidders(long lotId) throws DaoException;

    /**
     * Method designed for saving {@link com.epam.auction.model.Lot} bidders.
     *
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    void saveLotBidder(User bidder, long lotId) throws DaoException;
}
