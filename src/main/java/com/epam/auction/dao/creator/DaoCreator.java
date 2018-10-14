package com.epam.auction.dao.creator;

import com.epam.auction.dao.impl.LotDaoImpl;
import com.epam.auction.dao.impl.LotPhotoDaoImpl;
import com.epam.auction.dao.impl.UserDaoImpl;
import com.epam.auction.database.ConnectionPool;

import java.sql.Connection;

public class DaoCreator implements AutoCloseable {
    private ConnectionPool connectionPool;
    private Connection connection;

    public DaoCreator() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    public UserDaoImpl getUserDaoImpl() {
        return new UserDaoImpl(connection);
    }

    public LotDaoImpl getLotDaoImpl() {
        return new LotDaoImpl(connection);
    }

    public LotPhotoDaoImpl getLotPhotoDaoImpl() {
        return new LotPhotoDaoImpl(connection);
    }

    @Override
    public void close() {
        connectionPool.returnConnection(connection);
    }
}
