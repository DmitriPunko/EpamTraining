package com.epam.auction.builder;

import com.epam.auction.exception.DaoException;
import com.epam.auction.model.RoleEnum;
import com.epam.auction.model.User;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Designed to build an object of type {@link com.epam.auction.model.User} with specified characteristics.
 */
public class UserBuilder implements Builder<User> {

    /**
     * Builds an object of type User with properties.
     *
     * @param resultSet Instance of {@link java.sql.ResultSet} with property set to build an object type User.
     * @return Returns built object type User.
     * @throws DaoException Throws when SQL Exception is caught.
     */
    @Override
    public User build(ResultSet resultSet) throws DaoException {
        try {
            long id = resultSet.getLong(User.ID);
            String firstName = resultSet.getString(User.FIRST_NAME);
            String lastName = resultSet.getString(User.LAST_NAME);
            String userName = resultSet.getString(User.USERNAME);
            String email = resultSet.getString(User.EMAIL);
            String password = resultSet.getString(User.PASSWORD);

            String roleString = resultSet.getString(User.ROLE);
            roleString = roleString.toUpperCase();
            RoleEnum role = RoleEnum.valueOf(roleString);

            boolean isBanned = resultSet.getBoolean(User.IS_BANNED);

            BigDecimal balance = resultSet.getBigDecimal(User.BALANCE);

            return new User(id, firstName, lastName, userName, email, password, role, isBanned, balance);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
