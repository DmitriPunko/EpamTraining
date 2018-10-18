package com.epam.auction.builder;

import com.epam.auction.exception.DaoException;
import com.epam.auction.model.RoleEnum;
import com.epam.auction.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserBuilderTest {
    private static final long USER_ID = 1;
    private static final String FIRST_NAME = "Joseph";
    private static final String LAST_NAME = "Bobchich";
    private static final String USERNAME = "josbob";
    private static final String PASSWORD = "josbob12345";
    private static final String EMAIL = "josbob@gmail.com";
    private static final String USER = "user";
    private static final RoleEnum ROLE = RoleEnum.USER;
    private static final boolean IS_BANNED = false;
    private static final BigDecimal BALANCE = new BigDecimal(123.45);

    private static final User EXPECTED_USER = new User(
            USER_ID,
            FIRST_NAME,
            LAST_NAME,
            USERNAME,
            EMAIL,
            PASSWORD,
            ROLE,
            IS_BANNED,
            BALANCE
    );

    @Test
    public void shouldBuildAndReturnUserWithParameters() throws SQLException, DaoException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong(User.ID)).thenReturn(USER_ID);
        when(resultSet.getString(User.FIRST_NAME)).thenReturn(FIRST_NAME);
        when(resultSet.getString(User.LAST_NAME)).thenReturn(LAST_NAME);
        when(resultSet.getString(User.USERNAME)).thenReturn(USERNAME);
        when(resultSet.getString(User.PASSWORD)).thenReturn(PASSWORD);
        when(resultSet.getString(User.EMAIL)).thenReturn(EMAIL);
        when(resultSet.getBoolean(User.IS_BANNED)).thenReturn(IS_BANNED);
        when(resultSet.getString(User.ROLE)).thenReturn(USER);
        when(resultSet.getBigDecimal(User.BALANCE)).thenReturn(BALANCE);

        UserBuilder userBuilder = new UserBuilder();
        User actualUser = userBuilder.build(resultSet);

        Assert.assertEquals(EXPECTED_USER, actualUser);
    }
}