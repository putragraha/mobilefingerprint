package com.klk.mobilefingerprint.constantvalues;

import android.Manifest;

public class SettingsConfig {
    public static final String URI_PACKAGE = "package";
    public static final String[] PERMISSION_LIST = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
}
