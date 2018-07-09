package com.klk.mobilefingerprint.utils;

import com.klk.mobilefingerprint.constantvalues.DateTime;
import com.klk.mobilefingerprint.services.CalendarSetting;

import java.util.Calendar;

public class CalendarHelper implements CalendarSetting{

    private static final String TAG = CalendarHelper.class.getSimpleName();
    private Calendar mCalendar;

    public CalendarHelper(Calendar calendar){
        this.mCalendar = calendar;
    }

    @Override
    public void setCalendar(int hour, int minute, int second) {
        mCalendar.set(Calendar.HOUR_OF_DAY, DateTime.MIDNIGHT_HOUR);
        mCalendar.set(Calendar.MINUTE, DateTime.LAST_MINUTE);
        mCalendar.set(Calendar.SECOND, DateTime.MIDDLE_SECOND);
    }
}
