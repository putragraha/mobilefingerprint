package com.klk.mobilefingerprint.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

public class AlarmHelper {

    private static final String TAG = DateHelper.class.getSimpleName();
    private Context mContext;

    public  AlarmHelper(Context context){
        this.mContext = context;
    }

    public void setAlarm(Class javaClass, long interval, Calendar calendar){
        Intent intent = new Intent(mContext, javaClass);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, 0);
        AlarmManager alarm = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, pendingIntent);
    }
}
