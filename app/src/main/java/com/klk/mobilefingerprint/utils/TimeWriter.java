package com.klk.mobilefingerprint.utils;

import com.klk.mobilefingerprint.constantvalues.DateTime;
import com.klk.mobilefingerprint.services.DateTimeWriterService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeWriter implements DateTimeWriterService
{
    private static final String TAG = TimeWriter.class.getSimpleName();
    private SimpleDateFormat mSimpleDateFormat;

    public TimeWriter(){
        mSimpleDateFormat = new SimpleDateFormat(DateTime.TIME_FORMAT);
    }

    @Override
    public String getText(Date date) {
        String timeString = mSimpleDateFormat.format(date);
        return timeString;
    }
}
