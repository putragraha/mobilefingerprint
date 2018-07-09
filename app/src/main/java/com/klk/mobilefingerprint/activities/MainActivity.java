package com.klk.mobilefingerprint.activities;

import android.app.AlarmManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.constantvalues.DateTime;
import com.klk.mobilefingerprint.receivers.DailyDateReceiver;
import com.klk.mobilefingerprint.utils.AlarmHelper;
import com.klk.mobilefingerprint.utils.CalendarOperator;
import com.klk.mobilefingerprint.utils.DateWriter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private String mCurrDate;
    private TextView mDateText;

    private Calendar mCalendar;
    private Date mDate;

    private DateWriter mDateWriter = new DateWriter();
    private CalendarOperator mCalendarOperator = new CalendarOperator();
    private AlarmHelper mAlarmHelper = new AlarmHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        // setAddDateDaily();
    }

    private void init(){
        mDateText = (TextView) findViewById(R.id.tvDate);
        mDate = new Date();
        mCalendar = new GregorianCalendar();

        String currDate = mDateWriter.getDateText(mDate);
        String currDay = mDateWriter.getDayOfDate(mCalendar);
        mDateText.setText(currDay + ", " + currDate);
    }

    private void setAddDateDaily(){
        mCalendarOperator.setCalendar(mCalendar, DateTime.MIDNIGHT_HOUR, DateTime.LAST_MINUTE, DateTime.MIDDLE_SECOND);
        mAlarmHelper.setAlarm(this, DailyDateReceiver.class, AlarmManager.INTERVAL_DAY, mCalendar);
    }
}
