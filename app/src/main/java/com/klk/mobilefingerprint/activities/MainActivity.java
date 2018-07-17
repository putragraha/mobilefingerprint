package com.klk.mobilefingerprint.activities;

import android.app.AlarmManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.constantvalues.DateTime;
import com.klk.mobilefingerprint.components.AttendanceDialog;
import com.klk.mobilefingerprint.receivers.DailyDateReceiver;
import com.klk.mobilefingerprint.components.AlarmHelper;
import com.klk.mobilefingerprint.utils.CalendarOperator;
import com.klk.mobilefingerprint.utils.DateWriter;
import com.klk.mobilefingerprint.utils.NextTransitioner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mDateText;

    private Calendar mCalendar;
    private Date mDate;

    private DateWriter mDateWriter = new DateWriter();
    private CalendarOperator mCalendarOperator = new CalendarOperator();
    private NextTransitioner mNextTransitioner = new NextTransitioner();

    private AlarmHelper mAlarmHelper = new AlarmHelper();
    private AttendanceDialog mAttendanceDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        // setAddDateDaily();
    }

    private void init() {
        mDateText = (TextView) findViewById(R.id.tvDate);
        mDate = new Date();
        mCalendar = new GregorianCalendar();

        String currDate = mDateWriter.getText(mDate);
        String currDay = mDateWriter.getDayOfDate(this, mCalendar);
        mDateText.setText(currDay + ", " + currDate);

        mAttendanceDialog = new AttendanceDialog(this, this);
    }

    private void setAddDateDaily() {
        mCalendarOperator.setCalendar(mCalendar, DateTime.MIDNIGHT_HOUR, DateTime.LAST_MINUTE, DateTime.MIDDLE_SECOND);
        mAlarmHelper.setAlarm(this, DailyDateReceiver.class, AlarmManager.INTERVAL_DAY, mCalendar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menuAdmin:
                mNextTransitioner.animate(this, LoginAdminActivity.class);
                return true;
            case R.id.menuHistory:
                mAttendanceDialog.call();
                Toast.makeText(this, "History under construction", Toast.LENGTH_LONG).show();
                return true;
            default:
                return true;
        }
    }
}
