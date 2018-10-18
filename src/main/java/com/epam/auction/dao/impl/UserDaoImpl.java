package com.epam.auction.dao.impl;

import com.epam.auction.builder.UserBuilder;
import com.epam.auction.dao.AbstractDao;
import com.epam.auction.dao.UserDao;
import com.epam.auction.exception.DaoException;
import com.epam.auction.model.Identifiable;
import com.epam.auction.model.RoleEnum;
import com.epam.auction.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * Class is an implementation of access to user database and provides methods to work with it.
 */
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final String TABLE_NAME = "user";
    private static final String ALL_USERS_QUERY = "SELECT * FROM user WHERE role = 'user'";
    private static final String USERNAME_AND_PASSWORD_QUERY = "SELECT * FROM user WHERE username = ? AND password = ?";
    private static final String LOT_BIDDERS_QUERY = "SELECT" +
            " id_user, first_name, last_name, username, password, email, role, is_banned, balance" +
            " FROM bidder" +
            " LEFT JOIN user" +
            " ON user_id_user = user.id_user" +
            " WHERE lot_id_lot = ?";
    private static final String INSERT_QUERY = "INSERT INTO user (id_user, first_name, last_name, username, email," +
            " role, is_banned, balance)" +
            " VALUES(?,?,?,?,?,?,?,?) " +
            " ON DUPLICATE KEY" +
            " UPDATE id_user = VALUES(id_user), first_name = VALUES(first_name), last_name = VALUES(last_name)," +
            " username = VALUES(username), email = VALUES(email), role = VALUES(role)," +
            " is_banned = VALUES(is_banned), balance = VALUES(balance)";
    private static final String INSERT_BIDDER_QUERY = "INSERT INTO bidder (lot_id_lot, user_id_user) VALUES(?,?)";


    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    /**
     * The method designed for the process of saving a {@link User} object in database.
     *
     * @param item an {@link Identifiable} {@link User} object that should be saved to the database.
     * @return created user identifier in database.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    @Override
    public long save(Identifiable item) throws DaoException {
        User user = (User) item;

        long idUser = user.getIdUser();
        String idUserString = String.valueOf(idUser);

        String fistName = user.getFirstName();
        String lastName = user.getLastName();
        String username = user.getUserName();
        String email = user.getEmail();


        RoleEnum roleEnum = user.getRole();
        String roleString = roleEnum.getValue();

        boolean isBanned = user.isBanned();
        String isBannedString = isBanned ? "1" : "0";

        BigDecimal balance = user.getBalance();
        String balanceString = balance.toString();

        return executeUpdate(INSERT_QUERY, idUserString, fistName, lastName, username, email,
                roleString, isBannedString, balanceString);
    }

    /**
     * Method designed for searching user depends on user login and password.
     *
     * @param login    is a {@link String} object that contains user login
     * @param password is a {@link String} object that contains user password
     * @return a {@link Optional} object with finding {@link User} inside.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {

        String encryptedPassword = DigestUtils.sha256Hex(password);
        return executeQueryForSingleResult(USERNAME_AND_PASSWORD_QUERY, new UserBuilder(), login, encryptedPassword);

    }

    /**
     * Method designed for searching users.
     *
     * @return a {@link List} implementation with a {@link User} objects.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    @Override
    public List<User> findAllUsers() throws DaoException {
        return executeQuery(ALL_USERS_QUERY, new UserBuilder());
    }

    /**
     * Method designed for searching {@link com.epam.auction.model.Lot} bidders
     * depends on {@link com.epam.auction.model.Lot} identifier.
     *
     * @param lotId is an identifier of {@link com.epam.auction.model.Lot}
     * @return a {@link List} implementation with a {@link User} objects.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    @Override
    public List<User> findLotBidders(long lotId) throws DaoException {
        String lotIdString = String.valueOf(lotId);
        return executeQuery(LOT_BIDDERS_QUERY, new UserBuilder(), lotIdString);
    }

    /**
     * Method designed for saving {@link com.epam.auction.model.Lot} bidders.
     *
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    @Override
    public void saveLotBidder(User bidder, long lotId) throws DaoException {
        String lotIdString = String.valueOf(lotId);

        long bidderId = bidder.getId();
        String bidderIdString = String.valueOf(bidderId);

        executeUpdate(INSERT_BIDDER_QUERY, lotIdString, bidderIdString);
    }
}
