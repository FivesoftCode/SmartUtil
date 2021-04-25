package com.fivesoft.smartutil;

import java.util.Calendar;

public class C {

    public static long now(){
        return Calendar.getInstance().getTimeInMillis();
    }

    public static final long MINUTE = 60000L;
    public static final long HOUR = 3600000L;
    public static final long DAY = 86400000L;
    public static final long WEEK = 604800000L;
    public static final long MONTH = 2419200000L;
    public static final long YEAR = 29030400000L;

    public static final long THREE_MINUTES = MINUTE * 3;
    public static final long FIVE_MINUTES = MINUTE * 5;
    public static final long TEN_MINUTES = MINUTE * 10;
    public static final long FIFTEEN_MINUTES = MINUTE * 15;
    public static final long HALF_AN_HOUR = MINUTE * 30;

}
