package com.epam.auction.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeParser {

    private static final String PATTERN = "yyyy-MM-dd HH:mm";

    public static Date parse(String dateTime) {
        dateTime = dateTime.replace('T', ' ');
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(PATTERN);
        Date date;
        try {
            date = format.parse(dateTime);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Illegal date format");
        }
        return date;
    }

    public static String parse(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN);
        return dateFormat.format(date);
    }
}
