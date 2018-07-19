package com.klk.mobilefingerprint.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.constantvalues.RequestCode;
import com.klk.mobilefingerprint.constantvalues.SettingsConfig;

public class OpenSettingDialog extends AlertDialog.Builder {

    private Activity mActivity;
    private Context mContext;

    public OpenSettingDialog(final Activity activity, final Context context) {
        super(context);
        this.mActivity = activity;
        this.mContext = context;

        String title = context.getResources().getString(R.string.label_open_setting_dialog_title);
        String message = context.getResources().getString(R.string.label_open_setting_dialog_message);
        String gotosetting = context.getResources().getString(R.string.label_go_to_setting);
        String cancel = context.getResources().getString(R.string.label_confirm_cancel);

        setTitle(title);
        setMessage(message);

        setPositiveButton(gotosetting, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                openSettings();
            }
        });

        setNegativeButton(cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
    }

    private void openSettings(){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts(SettingsConfig.URI_PACKAGE, mContext.getPackageName(), null);
        intent.setData(uri);
        mActivity.startActivityForResult(intent, RequestCode.OPEN_SETTINGS_REQUEST);
    }
}
