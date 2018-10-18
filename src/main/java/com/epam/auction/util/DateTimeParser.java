package com.epam.auction.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  Designed for date and time parsing.
 */
public class DateTimeParser {

    private static final String PATTERN = "yyyy-MM-dd HH:mm";

    /**
     * The method parsing {@link String} object in "yyyy-MM-dd HH:mm" pattern into {@link Date} object.
     *
     * @param dateTime a {@link String} object that contain date and time that should be parsed.
     * @return a {@link Date} object with given date and time.
     */
    public static Date parse(String dateTime) {
        dateTime = dateTime.replace('T', ' ');
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(PATTERN);
        Date date;
        try {
            date = format.parse(dateTime);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Illegal date format!");
        }
        return date;
    }

    /**
     * The method parsing {@link Date} object into {@link String} object in "yyyy-MM-dd HH:mm" pattern.
     *
     * @param date a {@link Date} that should be represented in form of {@link String} object with pattern.
     * @return a {@link String} object in  that contains given date and time with pattern.
     */
    public static String parse(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN);
        return dateFormat.format(date);
    }
}
