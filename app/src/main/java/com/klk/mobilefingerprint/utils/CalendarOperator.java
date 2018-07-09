package com.klk.mobilefingerprint.utils;

import com.klk.mobilefingerprint.constantvalues.DateTime;
import com.klk.mobilefingerprint.services.CalendarOperatorService;

import java.util.Calendar;
import java.util.Date;

public class CalendarOperator implements CalendarOperatorService {

    private static final String TAG = CalendarOperator.class.getSimpleName();

    public CalendarOperator(){
    }

    @Override
    public void setCalendar(Calendar calendar, int hour, int minute, int second) {
        calendar.set(Calendar.HOUR_OF_DAY, DateTime.MIDNIGHT_HOUR);
        calendar.set(Calendar.MINUTE, DateTime.LAST_MINUTE);
        calendar.set(Calendar.SECOND, DateTime.MIDDLE_SECOND);
    }

    @Override
    public Date addDate(Calendar calendar, Date date, int increment) {
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, increment);
        Date addedDate = calendar.getTime();

        return addedDate;
    }

}
