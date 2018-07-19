package com.klk.mobilefingerprint.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.klk.mobilefingerprint.constantvalues.LocationConfig;
import com.klk.mobilefingerprint.dialogs.LocationOnDialog;
import com.klk.mobilefingerprint.dialogs.OpenSettingDialog;

public class LocationListenerHelper implements LocationListener {

    private static final String TAG = LocationListenerHelper.class.getSimpleName();

    private Activity mActivity;
    private Context mContext;
    private LocationManager mLocationManager;
    private Location mLocation;

//    public boolean isLocationEnabled = false;

    private double mLatitude, mLongitude;

    public LocationListenerHelper(Activity activity, Context context) {
        this.mContext = context;
        this.mActivity = activity;
        checkLocation();
    }

    private void checkLocation() {
        boolean isGPSEnabled = false;
        boolean isNetworkEnabled = false;

        try {
            mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
            // TODO : is it possible to check null value of location manager
            if (mLocationManager != null) {
                isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            }

            if (!isGPSEnabled && !isNetworkEnabled) {
//                isLocationEnabled = false;
//                String gpsWarningMsg = mContext.getResources().getString(R.string.warning_gps_disabled);
//                Toast.makeText(mContext, gpsWarningMsg, Toast.LENGTH_LONG).show();
                askTurnOnLocation();
            } else {
//                isLocationEnabled = true;

                if (isNetworkEnabled) {
                    requestLocation(LocationManager.NETWORK_PROVIDER);
                }

                if (isGPSEnabled) {
                    requestLocation(LocationManager.GPS_PROVIDER);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

//        return mLocation;
    }

    private void requestLocation(String provider) {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationManager.requestLocationUpdates(provider,
                    LocationConfig.MIN_TIME_FOR_UPDATES,
                    LocationConfig.MIN_DISTANCE_TO_REQUEST_LOCATION,
                    this);

            if (mLocationManager != null) {
                mLocation = mLocationManager.getLastKnownLocation(provider);

                if (mLocation != null) {
                    mLatitude = mLocation.getLatitude();
                    mLongitude = mLocation.getLongitude();
                }
            }
        } else {
            OpenSettingDialog openSettingDialog = new OpenSettingDialog(mActivity, mContext);
            openSettingDialog.show();
        }
    }

    public double getLatitude(){
        if (mLocation != null){
            mLatitude = mLocation.getLatitude();
        }

        return mLatitude;
    }

    public double getLongitude(){
        if (mLocation != null){
            mLongitude = mLocation.getLongitude();
        }

        return mLongitude;
    }

    private void askTurnOnLocation(){
        LocationOnDialog dialog = new LocationOnDialog(mContext);
        dialog.show();
    }

    @Override
    public void onLocationChanged(Location location) {
        this.mLocation = location;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
