package com.epam.auction.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryPreparer {

    public static PreparedStatement prepare(PreparedStatement preparedStatement, String... params) throws SQLException {

        int length = params.length;
        for (int i = 0; i < length; i++) {
            preparedStatement.setString(i + 1, params[i]);
        }

        return preparedStatement;
    }

}
