package com.klk.mobilefingerprint.utils;

import com.klk.mobilefingerprint.constantvalues.DateTime;
import com.klk.mobilefingerprint.services.DateControl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper implements DateControl {

    private static final String TAG = DateHelper.class.getSimpleName();
    private SimpleDateFormat mSimpleDateFormat;

    public DateHelper(){
        mSimpleDateFormat = new SimpleDateFormat(DateTime.DATE_FORMAT);
    }

    @Override
    public String getDateText(Date date) {
        String dateString = mSimpleDateFormat.format(date);
        return dateString;
    }

    @Override
    public String getNextDayOfDate(Calendar calendar, Date date) {
        CalendarHelper calendarHelper = new CalendarHelper();
        Date addedDate = calendarHelper.addDate(calendar, date, 1);
        String dateString = getDateText(addedDate);

        return dateString;
    }

}
