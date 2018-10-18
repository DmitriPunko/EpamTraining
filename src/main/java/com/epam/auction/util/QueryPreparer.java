package com.epam.auction.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *  Designed for query preparing.
 */
public class QueryPreparer {

    /**
     *  The method preparing query from parametrized format into simple format by replacing "?" sign on given parameter.
     *
     * @param preparedStatement a {@link PreparedStatement} object that contains query that should be prepared.
     * @param params  a {@link String} object that contains parameters that should be substituted instead of "?" sign.
     * @throws SQLException Signal that an exception that provides information on a database has occurred.
     */
    public static void prepare(PreparedStatement preparedStatement, String... params) throws SQLException {

        int length = params.length;
        for (int i = 0; i < length; i++) {
            preparedStatement.setString(i + 1, params[i]);
        }

    }

}
