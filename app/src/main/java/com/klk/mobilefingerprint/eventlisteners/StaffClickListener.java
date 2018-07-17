package com.klk.mobilefingerprint.eventlisteners;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

import com.klk.mobilefingerprint.components.ConfirmEnrollAlertDialog;

public class StaffClickListener implements View.OnClickListener {

    private ConfirmEnrollAlertDialog confirmEnrollAlertDialog;
    private Context mContext;

    public StaffClickListener (Context context){
        this.mContext = context;
        confirmEnrollAlertDialog = new ConfirmEnrollAlertDialog(mContext);
    }

    @Override
    public void onClick(View view) {
        final AlertDialog alertDialog = confirmEnrollAlertDialog.create();
        alertDialog.show();
    }
}
