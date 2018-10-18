package com.epam.auction.dao.impl;

import com.epam.auction.builder.LotBuilder;
import com.epam.auction.dao.AbstractDao;
import com.epam.auction.dao.DynamicQueryBuilder;
import com.epam.auction.dao.LotDao;
import com.epam.auction.exception.DaoException;
import com.epam.auction.model.*;
import com.epam.auction.util.DateTimeParser;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.*;

/**
 *  Class is an implementation of access to lot database and provides methods to work with it.
 */
public class LotDaoImpl extends AbstractDao<Lot> implements LotDao {

    private static final String DATE_OF_START_FROM = "date_of_start_from";
    private static final String DATE_OF_END_TO = "date_of_end_to";

    private static final String ALL_LOTS_BY_USER_ID_QUERY = "SELECT * FROM lot WHERE owner_id = ?";
    private static final String INSERT_QUERY = "INSERT INTO lot (id_lot, price, date_of_start, date_of_end, brand, model," +
            " class, year_of_issue, color, engine_volume, is_damaged, auction_type, owner_id, status)" +
            " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?) " +
            " ON DUPLICATE KEY" +
            " UPDATE id_lot = VALUES(id_lot), price = VALUES(price), date_of_start = VALUES(date_of_start)," +
            " date_of_end = VALUES(date_of_end), brand = VALUES(brand), model = VALUES(model), class =  VALUES(class)," +
            " year_of_issue = VALUES(year_of_issue), color = VALUES(color), engine_volume = VALUES(engine_volume)," +
            " is_damaged = VALUES(is_damaged), auction_type = VALUES(auction_type), owner_id = VALUES(owner_id)," +
            " status = VALUES(status)";
    private static final String BID_LOT_QUERY = "UPDATE lot SET lot.price = ? WHERE lot.id_lot = ? AND price = ?";

    private static final String TABLE_NAME = "lot";
    private static final String ALL = "All";

    public LotDaoImpl(Connection connection) {
        super(connection);
    }


    /**
     * The method designed for the process of saving a {@link Lot} object in database.
     *
     * @param item an {@link Identifiable} {@link Lot} object that should be saved to the database.
     * @return created lot identifier in database.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    @Override
    public long save(Identifiable item) throws DaoException {
        Lot lot = (Lot) item;

        long idLot = lot.getIdLot();
        String idLotString = String.valueOf(idLot);

        BigDecimal price = lot.getPrice();
        String priceString = price.toString();

        Date dateOfStart = lot.getDateOfStart();
        String dateOfStartString = DateTimeParser.parse(dateOfStart);

        Date dateOfEnd = lot.getDateOfEnd();
        String dateOfEndString = DateTimeParser.parse(dateOfEnd);

        String brand = lot.getBrand();
        String model = lot.getModel();
        String classOfLot = lot.getClassOfLot();

        int yearOfIssue = lot.getYearOfIssue();
        String yearOfIssueString = String.valueOf(yearOfIssue);

        ColorEnum color = lot.getColorEnum();
        String colorString = color.getValue();

        BigDecimal engineVolume = lot.getEngineVolume();
        String engineVolumeString = engineVolume.toString();

        boolean damaged = lot.isDamaged();
        String isDamagedString = damaged ? "1" : "0";

        AuctionTypeEnum auctionType = lot.getAuctionType();
        String auctionTypeString = auctionType.getValue();

        long ownerId = lot.getOwnerId();
        String ownerIdString = String.valueOf(ownerId);

        LotStatusEnum lotStatusEnum = lot.getStatus();
        String losStatusString = lotStatusEnum != null ?
                lotStatusEnum.getValue() :
                LotStatusEnum.PROCESSING.getValue();

        return executeUpdate(INSERT_QUERY, idLotString, priceString, dateOfStartString, dateOfEndString, brand, model,
                classOfLot, yearOfIssueString, colorString, engineVolumeString, isDamagedString, auctionTypeString,
                ownerIdString, losStatusString);
    }

    /**
     *
     * @return Name of the table designed for storage {@link Lot}.
     */
    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    /**
     *  Method designed for searching user lots depends on user identifier.
     *
     * @param id - User identifier in database
     * @return an {@link List} implementation with an user {@link Lot} objects.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    @Override
    public List<Lot> findAllByUserId(long id) throws DaoException {
        String idParameter = String.valueOf(id);

        return executeQuery(ALL_LOTS_BY_USER_ID_QUERY, new LotBuilder(), idParameter);
    }

    /**
     * The method searches for all active (which are in the auction) lots.
     *
     * @return an {@link List} implementation with {@link Lot} objects.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    @Override
    public List<Lot> findAllActive() throws DaoException {
        Date currentDate = new Date();
        String currentDateString = DateTimeParser.parse(currentDate);

        Map<String, String> parameters = new HashMap<>();
        parameters.put(Lot.STATUS, LotStatusEnum.CONFIRMED.getValue());
        parameters.put(DATE_OF_START_FROM, currentDateString);
        parameters.put(DATE_OF_END_TO, currentDateString);

        return findByParameters(parameters);
    }

    /**
     * The method searches for lots with given parameters.
     *
     * @param parameters a {@link Map} object that maps keys(name of parameter) to values of parameters.
     * @return an {@link List} implementation with {@link Lot} objects.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    @Override
    public List<Lot> findByParameters(Map<String, String> parameters) throws DaoException {
        Map<String, String> processedParameters = new HashMap<>();

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String value = entry.getValue();
            if (!ALL.equals(value)) {
                String key = entry.getKey();
                processedParameters.put(key, value);
            }
        }

        String query = DynamicQueryBuilder.build(processedParameters);

        Collection<String> values = processedParameters.values();

        int size = processedParameters.size();
        String[] parameterValues = new String[size];
        values.toArray(parameterValues);

        return executeQuery(query, new LotBuilder(), parameterValues);
    }

    /**
     * Makes a bid based on the type of auction.
     *
     * @param lot The {@link Lot} object of the lot on which you want to bet.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    @Override
    public void bid(Lot lot) throws DaoException {

        AuctionTypeEnum auctionType = lot.getAuctionType();
        if (AuctionTypeEnum.DIRECT.equals(auctionType)) {
           increasePrice(lot);
        } else {
            decreasePrice(lot);
        }

        long idLot = lot.getIdLot();
        String lotIdString = String.valueOf(idLot);

        BigDecimal oldPrice = lot.getPrice();
        String oldPriceString = oldPrice.toString();

        BigDecimal newPrice = lot.getPrice();
        String newPriceString = newPrice.toString();

        executeUpdate(BID_LOT_QUERY, newPriceString, lotIdString, oldPriceString);
    }

    /**
     * Increases the price of the lot depending on its current bid.
     *
     * @param lot an {@link Lot}object to be subject to price increase.
     */
    private void increasePrice(Lot lot) {
        BigDecimal currentBid = lot.getCurrentBid();
        BigDecimal oldPrice = lot.getPrice();
        BigDecimal newPrice = oldPrice.add(currentBid);
        lot.setPrice(newPrice);
    }

    /**
     * Decreases the price of the lot depending on its current bid and set {@link LotStatusEnum} of lot in
     * payment-waiting in the case of negative or zero price after bidding.
     *
     * @param lot an {@link Lot}object to be subject to price decrease.
     */
    private void decreasePrice(Lot lot) {
        BigDecimal currentBid = lot.getCurrentBid();
        BigDecimal oldPrice = lot.getPrice();
        BigDecimal newPrice = oldPrice.add(currentBid);

        if (newPrice.compareTo(BigDecimal.ZERO) <= 0) {
            newPrice = BigDecimal.ZERO;
            lot.setStatus(LotStatusEnum.PAYMENTWAITING);
        }

        lot.setPrice(newPrice);
    }
}
