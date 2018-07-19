package com.klk.mobilefingerprint.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.klk.mobilefingerprint.constantvalues.LocationConfig;
import com.klk.mobilefingerprint.dialogs.OpenSettingDialog;
import com.klk.mobilefingerprint.utils.LocationProvider;

public class GoogleApiClientHelper implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private Location mLocation;
    private GoogleApiClient mGoogleApiClient;
    //    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;

    private Activity mActivity;
    private Context mContext;

    public GoogleApiClientHelper(Activity activity, Context context, GoogleApiClient googleApiClient) {
        this.mActivity = activity;
        this.mContext = context;
        this.mGoogleApiClient = googleApiClient;
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mActivity);

        checkLocation();
        setupGoogleAPI();
        setupLocationRequest();
        setupLocationCallBack();
    }

    private void checkLocation() {
        LocationProvider mLocationProvider = new LocationProvider(mActivity, mContext);
        mLocationProvider.check();
    }

    private void setupGoogleAPI() {
        mGoogleApiClient = new GoogleApiClient
                .Builder(mContext)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mGoogleApiClient.connect();
    }

    private void setupLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(LocationConfig.UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(LocationConfig.FASTEST_UPDATE_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void setupLocationCallBack() {
        mLocationCallback = new LocationCallback() {
//            @Override
//            public void onLocationResult(LocationResult locationResult) {
//                super.onLocationResult(locationResult);
//                // location is received
//                mLocation = locationResult.getLastLocation();
//            }
        };
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, mLocationCallback, Looper.myLooper());
            mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

//            mFusedLocationClient.requestLocationUpdates(mLocationRequest,
//                    mLocationCallback, Looper.myLooper());
        } else {
            OpenSettingDialog openSettingDialog = new OpenSettingDialog(mActivity, mContext);
            openSettingDialog.show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public Location getLocation() {
        if (!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }
        return mLocation;
    }

    @Override
    public void onLocationChanged(Location location) {
        mLocation = location;
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
