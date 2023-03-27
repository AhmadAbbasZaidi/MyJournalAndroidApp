package com.assignment.myandroidjournal.view.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {

    public static String getMonth(long timestamp)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM");
        return sdf.format(timestamp);
    }
    public static String getDay(long timestamp)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd");
        return sdf.format(timestamp);
    }
    public static String getTime(long timestamp)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(timestamp);
    }

    public static String getCurrentDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(new Date());
    }

    public static String getCurrentMonthYear()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy");
        return sdf.format(new Date());
    }

    public static String getDate(long timestamp)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(timestamp);
    }
    public static String getMonthYear(long timestamp)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy");
        return sdf.format(timestamp);
    }

    public static long getOldDateString(int pastDays) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1*pastDays);
        return cal.getTime().getTime();
    }
}
