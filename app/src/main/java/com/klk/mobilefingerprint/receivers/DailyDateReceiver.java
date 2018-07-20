package com.klk.mobilefingerprint.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.klk.mobilefingerprint.utils.CalendarOperator;
import com.klk.mobilefingerprint.utils.CurrentDateWriter;

import java.util.Calendar;
import java.util.Date;

public class DailyDateReceiver extends BroadcastReceiver{

    private static final String TAG = DailyDateReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        CurrentDateWriter currentDateWriter = new CurrentDateWriter();
        CalendarOperator calendarOperator = new CalendarOperator();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();

        Date addedDate = calendarOperator.addDate(calendar, date, 1);
        String nextDay = currentDateWriter.getText(addedDate);

        Toast.makeText(context, nextDay, Toast.LENGTH_LONG).show();
    }
}
