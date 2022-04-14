package com.example.jaimegroup3;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

    public static String getDate(long milliSeconds, String outputFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(outputFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static String getTimeBefore15minutes(String currentDate) {

        SimpleDateFormat format = new SimpleDateFormat(Constant.yyyyMMdd_HHmm);
        try {
            // Get calendar set to current date and time with Europe time zone
            Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Europe/Berlin"));
            calendar.setTime(format.parse(currentDate));

            //Set calendar before 15 minutes
            calendar.add(Calendar.MINUTE, -15);
            //Formatter
            DateFormat formatter = new SimpleDateFormat(Constant.yyyyMMdd_HHmm);
            formatter.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
            return formatter.format(calendar.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}
