package com.klk.mobilefingerprint.utils;

import com.klk.mobilefingerprint.constantvalues.DateTime;
import com.klk.mobilefingerprint.services.DateWriter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper implements DateWriter {

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
    public String getDayOfDate(Calendar calendar) {
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String dayText = DateTime.DAY_NAME[day - 1];
        return dayText;
    }

}
