package com.epam.auction.util;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class DateTimeParserTest {

    private static final Calendar CALENDAR = Calendar.getInstance();
    private static final String STRING_DATE = "2015-08-23 14:55";
    private static Date dateObject;

    @BeforeClass
    public static void setDateToParse() {
        CALENDAR.set(2015, Calendar.AUGUST, 23, 14, 55, 0);
        CALENDAR.set(Calendar.MILLISECOND, 0);
        dateObject =  CALENDAR.getTime();
    }

    @Test
    public void shouldParseStringDateTimeAndReturnDateObject() {
        Date actualObjectDate = DateTimeParser.parse(STRING_DATE);
        Assert.assertEquals(dateObject, actualObjectDate);
    }

    @Test
    public void shouldParseDateObjectAndReturnStringDateTime() {

        String actualStringDate = DateTimeParser.parse(dateObject);
        Assert.assertEquals(STRING_DATE, actualStringDate);
    }
}