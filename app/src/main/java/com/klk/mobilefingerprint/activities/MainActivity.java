package com.klk.mobilefingerprint.activities;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.constantvalues.SettingsConfig;
import com.klk.mobilefingerprint.helpers.AttendanceDialogHelper;
import com.klk.mobilefingerprint.helpers.LocationListenerHelper;
import com.klk.mobilefingerprint.data.GlobalData;
import com.klk.mobilefingerprint.helpers.PermissionChecker;
import com.klk.mobilefingerprint.helpers.PermissionErrorChecker;
import com.klk.mobilefingerprint.utils.CurrentDateWriter;
import com.klk.mobilefingerprint.utils.NextTransitioner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RelativeLayout mRelativeLayout;
    private TextView mDateText;

    private CurrentDateWriter mCurrentDateWriter = new CurrentDateWriter();
    private NextTransitioner mNextTransitioner = new NextTransitioner();

    private AttendanceDialogHelper mAttendanceDialogHelper;
    private LocationListenerHelper mLocationHelper;
    private Location mLocation;

//    private CalendarOperator mCalendarOperator = new CalendarOperator();
//    private AlarmHelper mAlarmHelper = new AlarmHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (GlobalData.StaffList.isEmpty()) {
            GlobalData.getInstance().loadStaffData();
        }

        init();
        setDate();
        requestPermissions();
        // setAddDateDaily();
    }

    private void init() {
        mRelativeLayout = findViewById(R.id.mainLayout);
        mDateText = findViewById(R.id.tvDate);

        mLocationHelper = new LocationListenerHelper(this, this);
        mAttendanceDialogHelper = new AttendanceDialogHelper(this);

        // TODO : test purpose
        Button btnSimulate = findViewById(R.id.btnSimulate);
        btnSimulate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLocation = mLocationHelper.getLocation();
                if (mLocation != null) {
                    Toast.makeText(MainActivity.this, String.valueOf(mLocation.getLatitude()) + " | " + String.valueOf(mLocation.getLongitude()), Toast.LENGTH_SHORT).show();
                    mAttendanceDialogHelper.call();
                }
            }
        });
    }

    private void setDate() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();

        String currDate = mCurrentDateWriter.getText(date);
        String currDay = mCurrentDateWriter.getDayOfDate(this, calendar);
        String currDayDate = currDay + ", " + currDate;
        mDateText.setText(currDayDate);
    }

    private void requestPermissions() {
        Dexter.withActivity(this)
                .withPermissions(SettingsConfig.PERMISSION_LIST)
                .withListener(new PermissionChecker(this, this))
                .withErrorListener(new PermissionErrorChecker(this, mRelativeLayout))
                .onSameThread()
                .check();
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
//                mAttendanceDialogHelper.call();
                mNextTransitioner.animate(this, LoginHistoryAcitivity.class);
                return true;
            default:
                return true;
        }
    }
}
