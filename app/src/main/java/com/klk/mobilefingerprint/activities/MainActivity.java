package com.klk.mobilefingerprint.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.receivers.DailyDateReceiver;
import com.klk.mobilefingerprint.utils.DateHelper;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private String mCurrDate;
    private TextView mDateText;

    private PendingIntent mPendingIntent;

    private DateHelper mDateHelper = new DateHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDateText = (TextView) findViewById(R.id.tvDate);
//        Intent alarmIntent = new Intent(this, DailyDateReceiver.class);
//        mPendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
//        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

//        manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_DAY,
//                AlarmManager.INTERVAL_DAY, mPendingIntent);

//        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 2000, 10000, mPendingIntent);

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 30);
        Intent intent = new Intent(this, DailyDateReceiver.class);
        mPendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, mPendingIntent);

        Log.e(TAG, mDateHelper.getCurrDate());
    }
}
