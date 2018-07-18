package com.klk.mobilefingerprint.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.components.AttendanceDialog;
import com.klk.mobilefingerprint.data.GlobalData;
import com.klk.mobilefingerprint.utils.DateWriter;
import com.klk.mobilefingerprint.utils.NextTransitioner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private DateWriter mDateWriter = new DateWriter();
//    private CalendarOperator mCalendarOperator = new CalendarOperator();
    private NextTransitioner mNextTransitioner = new NextTransitioner();

//    private AlarmHelper mAlarmHelper = new AlarmHelper();
    private AttendanceDialog mAttendanceDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        if (GlobalData.getInstance().StaffList != null) { GlobalData.getInstance().loadStaffData(); }
        // setAddDateDaily();
    }

    private void init() {
        TextView dateText = findViewById(R.id.tvDate);
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();

        String currDate = mDateWriter.getText(date);
        String currDay = mDateWriter.getDayOfDate(this, calendar);
        String currDayDate = currDay + ", " + currDate;
        dateText.setText(currDayDate);

        mAttendanceDialog = new AttendanceDialog(this);
    }

//    private void setAddDateDaily() {
//        mCalendarOperator.setCalendar(mCalendar, DateTime.MIDNIGHT_HOUR, DateTime.LAST_MINUTE, DateTime.MIDDLE_SECOND);
//        mAlarmHelper.setAlarm(this, DailyDateReceiver.class, AlarmManager.INTERVAL_DAY, mCalendar);
//    }

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
