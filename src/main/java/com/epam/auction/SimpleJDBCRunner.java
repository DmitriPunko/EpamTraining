package com.epam.auction;

import com.epam.auction.util.DateTimeParser;
import org.apache.commons.codec.digest.DigestUtils;
import sun.security.provider.MD5;

import java.sql.SQLException;
import java.util.*;

public class SimpleJDBCRunner {

    public static final int anInt = ~220;

    public static final String URL = "jdbc:mysql://localhost:3306/auction?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "punkodima0";

    public static void main(String[] args) throws SQLException {

        int[][] ar = {{1,2,3},{4,2,3},{1,2,3}};
        System.out.println(Arrays.toString(ar));
    }


}