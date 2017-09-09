package com.haizhi.utils;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by admin on 2017/6/27.
 */
public class HbrainUtils {
    private static final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
    private static final DateTimeFormatter monthFormatter = DateTimeFormat.forPattern("yyyy-MM");

    public static String[] getDateStrArray(String dateFrom) {
        Calendar c = Calendar.getInstance();
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = new Date();

        try {
            myDate = dateFormat1.parse(dateFrom);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(myDate);
        ArrayList<String> dateList = new ArrayList<>();
        while (c.compareTo(Calendar.getInstance()) < 0) {
            dateList.add(dateFormat1.format(c.getTime()));
            c.add(Calendar.DATE, 1);
        }
        //return (String[])dateList.toArray();
        return dateList.toArray(new String[dateList.size()]);
    }

    public static String nextMonth(String month) {
        DateTime dt = monthFormatter.parseDateTime(month);
        return dt.plusMonths(1).toString(monthFormatter);
    }

    public static String nextDay(String date) {
        DateTime dt = dateFormatter.parseDateTime(date);
        return dt.plusDays(1).toString(dateFormatter);
    }

    public static String currentDate() {
        return (new DateTime(System.currentTimeMillis())).toString(dateFormatter);
    }

    public static String getQueryFromDAte(final String fromDate, final String monthNum) {
        if (StringUtils.isNotEmpty(fromDate)) {
            return fromDate;
        } else {
            return getQueryFromDAte(monthNum);
        }
    }

    public static String getQueryFromDAte(final String monthNum) {
        if (StringUtils.isNotEmpty(monthNum)) {
            DateTime dateTime = (new DateTime(System.currentTimeMillis())).minusMonths(Integer.parseInt(monthNum));
            return dateTime.toString(dateFormatter);
        } else {
            return null;
        }
    }
}
