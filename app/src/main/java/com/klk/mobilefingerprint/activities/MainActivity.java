package com.klk.mobilefingerprint.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.constantvalues.DateTime;
import com.klk.mobilefingerprint.receivers.DailyDateReceiver;
import com.klk.mobilefingerprint.utils.AlarmHelper;
import com.klk.mobilefingerprint.utils.CalendarHelper;
import com.klk.mobilefingerprint.utils.DateHelper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private String mCurrDate;
    private TextView mDateText;

    private Calendar mCalendar;

    private DateHelper mDateHelper = new DateHelper();
    private CalendarHelper mCalendarHelper;
    private AlarmHelper mAlarmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setAddDateDaily();
    }

    private void init(){
        mDateText = (TextView) findViewById(R.id.tvDate);
        mAlarmHelper = new AlarmHelper(this);
        mCalendar = new GregorianCalendar();
        mCalendarHelper = new CalendarHelper(mCalendar);
    }

    private void setAddDateDaily(){
        mCalendarHelper.setCalendar(DateTime.MIDNIGHT_HOUR, DateTime.LAST_MINUTE, DateTime.MIDDLE_SECOND);
        mAlarmHelper.setAlarm(DailyDateReceiver.class, AlarmManager.INTERVAL_DAY, mCalendar);
    }
}
