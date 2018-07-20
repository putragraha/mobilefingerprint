package com.klk.mobilefingerprint.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.constantvalues.LocationConfig;
import com.klk.mobilefingerprint.constantvalues.RequestCode;
import com.klk.mobilefingerprint.constantvalues.SettingsConfig;
import com.klk.mobilefingerprint.helpers.AttendanceDialogHelper;
import com.klk.mobilefingerprint.data.GlobalData;
import com.klk.mobilefingerprint.helpers.PermissionChecker;
import com.klk.mobilefingerprint.helpers.PermissionErrorChecker;
import com.klk.mobilefingerprint.utils.DateWriter;
import com.klk.mobilefingerprint.utils.NextTransitioner;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mRelativeLayout;
    private TextView mDateText;

    private DateWriter mDateWriter = new DateWriter();
    private NextTransitioner mNextTransitioner = new NextTransitioner();

    // TODO : For Location
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;

    private String mLastUpdateTime;

    // TODO : boolean flag to toggle the ui
    private Boolean mRequestingLocationUpdates;

    private AttendanceDialogHelper mAttendanceDialogHelper;

//    private CalendarOperator mCalendarOperator = new CalendarOperator();
//    private AlarmHelper mAlarmHelper = new AlarmHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (GlobalData.getInstance().StaffList.isEmpty()) {
            GlobalData.getInstance().loadStaffData();
        }

        init();
        setDate();
        requestPermissions();

        // setAddDateDaily();

        // TODO : restore the values from saved instance state
        restoreValuesFromBundle(savedInstanceState);
        startLocationUpdates();
    }

    private void init() {
        mRelativeLayout = findViewById(R.id.mainLayout);
        mDateText = findViewById(R.id.tvDate);

        mAttendanceDialogHelper = new AttendanceDialogHelper(this);

        // TODO : init for location
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mSettingsClient = LocationServices.getSettingsClient(this);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                // location is received
                mCurrentLocation = locationResult.getLastLocation();
                mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());

//                updateLocationUI();
            }
        };

        mRequestingLocationUpdates = false;

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(LocationConfig.UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(LocationConfig.FASTEST_UPDATE_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();

        // TODO : test purpose
        Button btnSimulate = findViewById(R.id.btnSimulate);
        btnSimulate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAttendanceDialogHelper.call();
                if (mCurrentLocation != null){
                    Toast.makeText(MainActivity.this, mCurrentLocation.getLatitude() + " | " + mCurrentLocation.getLongitude(), Toast.LENGTH_LONG ).show();
                }
            }
        });
    }

    private void setDate() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();

        String currDate = mDateWriter.getText(date);
        String currDay = mDateWriter.getDayOfDate(this, calendar);
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

    private void restoreValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("is_requesting_updates")) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean("is_requesting_updates");
            }

            if (savedInstanceState.containsKey("last_known_location")) {
                mCurrentLocation = savedInstanceState.getParcelable("last_known_location");
            }

            if (savedInstanceState.containsKey("last_updated_on")) {
                mLastUpdateTime = savedInstanceState.getString("last_updated_on");
            }
        }

//        updateLocationUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("is_requesting_updates", mRequestingLocationUpdates);
        outState.putParcelable("last_known_location", mCurrentLocation);
        outState.putString("last_updated_on", mLastUpdateTime);

    }

    private void startLocationUpdates() {
        mSettingsClient
                .checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i("MainActivity", "All location settings are satisfied.");

                        Toast.makeText(getApplicationContext(), "Started location updates!", Toast.LENGTH_SHORT).show();

                        //noinspection MissingPermission
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                mLocationCallback, Looper.myLooper());

//                        updateLocationUI();
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                Log.i("MainActivity", "Location settings are not satisfied. Attempting to upgrade " +
                                        "location settings ");
                                try {
                                    // Show the dialog by calling startResolutionForResult(), and check the
                                    // result in onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(MainActivity.this, RequestCode.REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i("Main Activity", "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e("Main Activity", errorMessage);

                                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                        }

//                        updateLocationUI();
                    }
                });
    }

    public void stopLocationUpdates() {
        // Removing location updates
        mFusedLocationClient
                .removeLocationUpdates(mLocationCallback)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Location updates stopped!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mRequestingLocationUpdates) {
            stopLocationUpdates();
        }
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
//                mAttendanceDialogHelper.call();
                mNextTransitioner.animate(this, LoginHistoryAcitivity.class);
                return true;
            default:
                return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case RequestCode.REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.e("MainActivity", "User agreed to make required location settings changes.");
                        // Nothing to do. startLocationupdates() gets called in onResume again.
                        mRequestingLocationUpdates = true;
                        startLocationUpdates();
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.e("MainActivity", "User chose not to make required location settings changes.");
                        mRequestingLocationUpdates = false;
                        break;
                }
                break;
        }
    }
}
