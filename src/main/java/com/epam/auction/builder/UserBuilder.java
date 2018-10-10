package com.epam.auction.builder;

import com.epam.auction.exception.DaoException;
import com.epam.auction.model.RoleEnum;
import com.epam.auction.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {
    @Override
    public User build(ResultSet resultSet) throws DaoException {
        try {
            long id = resultSet.getLong(User.ID);
            String firstName = resultSet.getString(User.FIRST_NAME);
            String lastName = resultSet.getString(User.LAST_NAME);
            String userName = resultSet.getString(User.USER_NAME);
            String email = resultSet.getString(User.EMAIL);
            String password = resultSet.getString(User.PASSWORD);

            String roleString = resultSet.getString(User.ROLE);
            roleString = roleString.toUpperCase();
            RoleEnum role = RoleEnum.valueOf(roleString);

            boolean isBanned = resultSet.getBoolean(User.IS_BANNED);

             return new User(id, firstName, lastName, userName, email, password, role, isBanned);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
