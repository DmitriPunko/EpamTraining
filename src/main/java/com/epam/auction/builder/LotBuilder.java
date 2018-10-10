package com.epam.auction.builder;

import com.epam.auction.exception.DaoException;
import com.epam.auction.model.AuctionTypeEnum;
import com.epam.auction.model.ColorEnum;
import com.epam.auction.model.Lot;
import com.epam.auction.model.LotStatusEnum;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class LotBuilder implements Builder<Lot> {

    @Override
    public Lot build(ResultSet resultSet) throws DaoException {
        try {
            long id = resultSet.getLong(Lot.ID);
            BigDecimal price = resultSet.getBigDecimal(Lot.PRICE);

            Timestamp dateOfStartTimeStamp = resultSet.getTimestamp(Lot.DATE_OF_START);
            long dateOfStartTimeMillisecond = dateOfStartTimeStamp.getTime();
            Date dateOfStart = new Date(dateOfStartTimeMillisecond);

            Timestamp dateOfEndTimeStamp = resultSet.getTimestamp(Lot.DATE_OF_END);
            long dateOfEndTimeMillisecond = dateOfEndTimeStamp.getTime();
            Date dateOfEnd = new Date(dateOfEndTimeMillisecond);

            String brand = resultSet.getString(Lot.BRAND);
            String model = resultSet.getString(Lot.MODEL);
            String classOfCar = resultSet.getString(Lot.CLASS);
            int yearOfIssue = resultSet.getInt(Lot.YEAR_OF_ISSUE);
            BigDecimal engineVolume = resultSet.getBigDecimal(Lot.ENGINE_VOLUME);
            boolean isDamaged = resultSet.getBoolean(Lot.IS_DAMAGED);

            String colorString = resultSet.getString(Lot.COLOR);
            colorString = colorString.toUpperCase();
            ColorEnum colorEnum = ColorEnum.valueOf(colorString);

            String auctionTypeString = resultSet.getString(Lot.AUCTION_TYPE);
            auctionTypeString = auctionTypeString.toUpperCase();
            AuctionTypeEnum auctionTypeEnum = AuctionTypeEnum.valueOf(auctionTypeString);

            long ownerId = resultSet.getLong(Lot.OWNER_ID);

            String statusString = resultSet.getString(Lot.STATUS);
            statusString = statusString.toUpperCase();
            LotStatusEnum status = LotStatusEnum.valueOf(statusString);

            return new Lot(id, price, dateOfStart, dateOfEnd, brand, model, classOfCar, yearOfIssue, colorEnum,
                    engineVolume, isDamaged, auctionTypeEnum, ownerId, status);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
