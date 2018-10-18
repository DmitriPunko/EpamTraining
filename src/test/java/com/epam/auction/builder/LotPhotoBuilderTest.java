package com.epam.auction.builder;

import com.epam.auction.exception.DaoException;
import com.epam.auction.model.LotPhoto;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LotPhotoBuilderTest {
    private static final long ID_PHOTO = 2;
    private static final long ID_LOT = 3;
    private static final String URL = "12345abcdef.jpg";

    private static final LotPhoto EXPECTED_LOT_PHOTO = new LotPhoto(ID_PHOTO, ID_LOT, URL);

    @Test
    public void shouldBuildAndReturnLotPhotoWithParameters() throws SQLException, DaoException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong(LotPhoto.ID_PHOTO)).thenReturn(ID_PHOTO);
        when(resultSet.getLong(LotPhoto.ID_LOT)).thenReturn(ID_LOT);
        when(resultSet.getString(LotPhoto.URL)).thenReturn(URL);

        LotPhotoBuilder lotPhotoBuilder = new LotPhotoBuilder();
        LotPhoto actualLotPhoto = lotPhotoBuilder.build(resultSet);

        Assert.assertEquals(EXPECTED_LOT_PHOTO, actualLotPhoto);

    }
}