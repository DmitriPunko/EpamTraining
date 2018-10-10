package com.epam.auction.dao;

import com.epam.auction.builder.Builder;
import com.epam.auction.exception.DaoException;
import com.epam.auction.factory.BuilderFactory;
import com.epam.auction.model.Identifiable;
import com.epam.auction.util.QueryPreparer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifiable> implements Dao {

    private Connection connection;

    private static final String GET_ALL_QUERY = "SELECT * FROM ";
    private String WHERE_ID_CONDITION = " WHERE id_" + getTableName() + "= ?";

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    protected abstract String getTableName();

    protected List<T> executeQuery(String query, Builder<T> builder, String... params) throws DaoException {

        List<T> items = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            QueryPreparer.prepare(preparedStatement, params);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                T item = builder.build(rs);
                items.add(item);
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }

        return items;
    }

    protected Optional<T> executeQueryForSingleResult(String query, Builder<T> builder, String... params) throws DaoException {

        List<T> items = executeQuery(query, builder, params);

        return items.size() == 1 ?
                Optional.of(items.get(0)) :
                Optional.empty();
    }

    protected long executeUpdate(String query, String... params) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            QueryPreparer.prepare(preparedStatement, params);

            preparedStatement.executeUpdate();

            ResultSet key = preparedStatement.getGeneratedKeys();
            key.next();
            return key.getLong(1);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<T> findById(Long id) throws DaoException {
        Builder builder = BuilderFactory.create(getTableName());
        String query = GET_ALL_QUERY + getTableName() + WHERE_ID_CONDITION;
        String stringId = String.valueOf(id);
        return executeQueryForSingleResult(query, builder, stringId);
    }

    @Override
    public List<T> findAll() throws DaoException {
        Builder builder = BuilderFactory.create(getTableName());
        String query = GET_ALL_QUERY + getTableName();
        return executeQuery(query, builder);
    }


    @Override
    public void removeById(Long id) throws DaoException {

    }
}
