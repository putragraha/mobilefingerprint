package com.klk.mobilefingerprint.utils;

import android.util.Log;

import com.klk.mobilefingerprint.constantvalues.DateTime;
import com.klk.mobilefingerprint.services.CurrentDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper implements CurrentDate {

    private static final String TAG = DateHelper.class.getSimpleName();
    private Date mDate;
    private SimpleDateFormat mSimpleDateFormat;

    public DateHelper(){
        mDate = Calendar.getInstance().getTime();
        mSimpleDateFormat = new SimpleDateFormat(DateTime.DATE_FORMAT);
    }

    public Date getDate(String dateText){
        Date date = null;
        try {
            date = mSimpleDateFormat.parse(dateText);
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
        }

        return date;
    }

    @Override
    public String getCurrDate() {
        String currDate = mSimpleDateFormat.format(mDate);
        return currDate;
    }
}
