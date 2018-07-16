package com.klk.mobilefingerprint.utils;

import android.content.Context;

import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.constantvalues.DateTime;
import com.klk.mobilefingerprint.services.DateTimeWriterService;
import com.klk.mobilefingerprint.services.DayNameWriterService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateWriter implements DateTimeWriterService, DayNameWriterService {

    private static final String TAG = DateWriter.class.getSimpleName();
    private SimpleDateFormat mSimpleDateFormat;

    public DateWriter(){
        mSimpleDateFormat = new SimpleDateFormat(DateTime.DATE_FORMAT);
    }

    @Override
    public String getText(Date date) {
        String dateString = mSimpleDateFormat.format(date);
        return dateString;
    }

    @Override
    public String getDayOfDate(Context context, Calendar calendar) {
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String[] days = context.getResources().getStringArray(R.array.day_name_array);
        String dayText = days[day - 1];
        return dayText;
    }

}
