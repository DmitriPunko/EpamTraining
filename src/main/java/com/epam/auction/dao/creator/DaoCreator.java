package com.epam.auction.dao.creator;

import com.epam.auction.dao.impl.LotDaoImpl;
import com.epam.auction.dao.impl.LotPhotoDaoImpl;
import com.epam.auction.dao.impl.UserDaoImpl;
import com.epam.auction.database.ConnectionPool;

import java.sql.Connection;

/**
 * Provides {@link AutoCloseable} creator of dao implementation class with connection to database for each.
 */
public class DaoCreator implements AutoCloseable {
    private ConnectionPool connectionPool;
    private Connection connection;

    public DaoCreator() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    /**
     *
     * @return an {@link UserDaoImpl} object with connection to database.
     */
    public UserDaoImpl getUserDaoImpl() {
        return new UserDaoImpl(connection);
    }

    /**
     *
     * @return an {@link LotDaoImpl} object with connection to database.
     */
    public LotDaoImpl getLotDaoImpl() {
        return new LotDaoImpl(connection);
    }

    /**
     *
     * @return an {@link LotPhotoDaoImpl} object with connection to database.
     */
    public LotPhotoDaoImpl getLotPhotoDaoImpl() {
        return new LotPhotoDaoImpl(connection);
    }

    /**
     *  Returns database connection to {@link ConnectionPool}
     */
    @Override
    public void close() {
        connectionPool.returnConnection(connection);
    }
}
