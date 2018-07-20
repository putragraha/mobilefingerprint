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

    private boolean isGPSEnabled = false, isNetworkEnabled = false;

    public LocationListenerHelper(Activity activity, Context context) {
        this.mContext = context;
        this.mActivity = activity;

        checkLocation();
    }

    private void checkLocation() {
        try {
            mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
            if (mLocationManager != null) {
                isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            }

            if (!isGPSEnabled && !isNetworkEnabled) {
                askTurnOnLocation();
            } else {
                requestLocation();
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void requestLocation(){
        // TODO : Bug on purpose
//        if (isNetworkEnabled)
        requestLocation(LocationManager.GPS_PROVIDER);
        //if (isNetworkEnabled)
        requestLocation(LocationManager.NETWORK_PROVIDER);
    }

    private void requestLocation(String provider) {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationManager.requestLocationUpdates(provider,
                    LocationConfig.MIN_TIME_FOR_UPDATES,
                    LocationConfig.MIN_DISTANCE_TO_REQUEST_LOCATION,
                    LocationListenerHelper.this);

            if (mLocationManager != null) {
                mLocation = mLocationManager.getLastKnownLocation(provider);
            }
        } else {
            OpenSettingDialog openSettingDialog = new OpenSettingDialog(mActivity, mContext);
            openSettingDialog.show();
        }
    }

    public Location getLocation(){
        if (mLocation != null){
            return mLocation;
        } else {
            askTurnOnLocation();
        }

        return null;
    }

    private void askTurnOnLocation(){
        LocationOnDialog dialog = new LocationOnDialog(mActivity, mContext);
        dialog.show();
        requestLocation();
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
