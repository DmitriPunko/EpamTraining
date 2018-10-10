package com.epam.auction.builder;

import com.epam.auction.exception.DaoException;
import com.epam.auction.model.LotPhoto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LotPhotoBuilder implements Builder<LotPhoto> {
    @Override
    public LotPhoto build(ResultSet resultSet) throws DaoException {
        try {
            long idPhoto = resultSet.getLong(LotPhoto.ID_PHOTO);
            long idLot = resultSet.getLong(LotPhoto.ID_LOT);
            String url = resultSet.getString(LotPhoto.URL);

            return new LotPhoto(idPhoto, idLot, url);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
