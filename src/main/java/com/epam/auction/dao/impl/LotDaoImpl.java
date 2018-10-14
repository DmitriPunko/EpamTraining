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
        String losStatusString = lotStatusEnum != null ? lotStatusEnum.getValue() : LotStatusEnum.PROCESSING.getValue();

        return executeUpdate(INSERT_QUERY, idLotString, priceString, dateOfStartString, dateOfEndString, brand, model,
                classOfLot, yearOfIssueString, colorString, engineVolumeString, isDamagedString, auctionTypeString,
                ownerIdString, losStatusString);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public List<Lot> findAllByUserId(long id) throws DaoException {
        String idParameter = String.valueOf(id);

        return executeQuery(ALL_LOTS_BY_USER_ID_QUERY, new LotBuilder(), idParameter);
    }

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

    @Override
    public void bid(Lot lot) throws DaoException {
        BigDecimal currentBid = lot.getCurrentBid();
        BigDecimal oldPrice = lot.getPrice();
        BigDecimal newPrice = oldPrice.add(currentBid);

        long idLot = lot.getIdLot();
        String lotIdString = String.valueOf(idLot);
        String oldPriceString = oldPrice.toString();
        String newPriceString = newPrice.toString();

        executeUpdate(BID_LOT_QUERY, newPriceString, lotIdString, oldPriceString);
    }
}
