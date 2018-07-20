package com.klk.mobilefingerprint.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.constantvalues.RequestCode;

public class LocationOnDialog extends AlertDialog.Builder {

    private static final String TAG = LocationOnDialog.class.getSimpleName();

    private Activity mActivity;

    public LocationOnDialog(Activity activity, Context context) {
        super(context);
        this.mActivity = activity;

        String title = context.getResources().getString(R.string.label_location_dialog_title);
        String message = context.getResources().getString(R.string.label_location_dialog_message);
        String yes = context.getResources().getString(R.string.label_confirm_yes);
        String no = context.getResources().getString(R.string.label_confirm_no);

        setTitle(title);
        setMessage(message);

        setPositiveButton(yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                openLocationSettings();
            }
        });

        setNegativeButton(no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
    }

    private void openLocationSettings(){
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        mActivity.startActivityForResult(intent, RequestCode.LOCATION_SETTINGS_REQUEST);
    }

}
