package com.klk.mobilefingerprint.utils;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.util.Log;

import com.klk.mobilefingerprint.dialogs.LocationOnDialog;
import com.klk.mobilefingerprint.services.DeviceCheckService;

public class LocationProvider implements DeviceCheckService {

    private static final String TAG = LocationProvider.class.getSimpleName();

    private Activity mActivity;
    private Context mContext;

    public LocationProvider (Activity activity, Context context){
        this.mActivity = activity;
        this.mContext = context;
    }

    @Override
    public void check() {
        LocationManager locationManager;
        boolean isGPSEnabled = false;
        boolean isNetworkEnabled = false;

        try {
            locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
            if (locationManager != null) {
                isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            }

            if (!isGPSEnabled && !isNetworkEnabled) {
                askTurnOnLocation();
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void askTurnOnLocation(){
        LocationOnDialog dialog = new LocationOnDialog(mActivity, mContext);
        dialog.show();
    }
}
