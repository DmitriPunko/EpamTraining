package com.epam.auction.dao;

import com.epam.auction.model.Lot;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DynamicQueryBuilder {

    private static final String ALL_LOTS_QUERY = "SELECT * FROM lot";
    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";

    private static final String AUCTION_TYPE_PARAMETER = "auction_type = ?";
    private static final String BRAND_PARAMETER = "brand = ?";
    private static final String YEAR_OF_ISSUE_FROM_PARAMETER = "year_of_issue > ?";
    private static final String YEAR_OF_ISSUE_TO_PARAMETER = "year_of_issue < ?";
    private static final String IS_DAMAGED_PARAMETER = "is_damaged = ?";
    private static final String STATUS_PARAMETER = "status = ?";
    private static final String DATE_OF_START_FROM_PARAMETER = "date_of_start < ?";
    private static final String DATE_OF_START_TO_PARAMETER = "date_of_start > ?";
    private static final String DATE_OF_END_TO_PARAMETER = "date_of_end > ?";
    private static final String DATE_OF_END_FROM_PARAMETER = "date_of_end < ?";

    private static final String YEAR_FROM = "year_of_issue_from";
    private static final String YEAR_TO = "year_of_issue_to";
    private static final String DATE_OF_START_FROM = "date_of_start_from";
    private static final String DATE_OF_START_TO = "date_of_start_to";
    private static final String DATE_OF_END_FROM = "date_of_end_from";
    private static final String DATE_OF_END_TO = "date_of_end_to";

    public static String build(Map<String, String> parameters) {
        if (parameters.size() == 0) {
            return ALL_LOTS_QUERY;
        }

        Set<String> keySet = parameters.keySet();
        Iterator<String> iterator = keySet.iterator();

        StringBuilder resultQuery = new StringBuilder(ALL_LOTS_QUERY + WHERE);
        int size = keySet.size();
        for (int i = 0; i < size; i++) {
            String key = iterator.next();

            if (i != 0) {
                resultQuery.append(AND);
            }

            String parameter = findWhereParameterByName(key);
            resultQuery.append(parameter);
        }

        return resultQuery.toString();
    }

    public static String findWhereParameterByName(String name) {

        String whereParameter;

        switch (name) {
            case Lot.AUCTION_TYPE:
                whereParameter = AUCTION_TYPE_PARAMETER;
                break;
            case Lot.BRAND:
                whereParameter = BRAND_PARAMETER;
                break;
            case YEAR_FROM:
                whereParameter = YEAR_OF_ISSUE_FROM_PARAMETER;
                break;
            case YEAR_TO:
                whereParameter = YEAR_OF_ISSUE_TO_PARAMETER;
                break;
            case Lot.IS_DAMAGED:
                whereParameter = IS_DAMAGED_PARAMETER;
                break;
            case Lot.STATUS:
                whereParameter = STATUS_PARAMETER;
                break;
            case DATE_OF_START_FROM:
                whereParameter = DATE_OF_START_FROM_PARAMETER;
                break;
            case DATE_OF_START_TO:
                whereParameter = DATE_OF_START_TO_PARAMETER;
                break;
            case DATE_OF_END_FROM:
                whereParameter = DATE_OF_END_FROM_PARAMETER;
                break;
            case DATE_OF_END_TO:
                whereParameter = DATE_OF_END_TO_PARAMETER;
                break;
            default:
                throw new IllegalArgumentException("Unknown name of parameter!");
        }

        return whereParameter;
    }
}
