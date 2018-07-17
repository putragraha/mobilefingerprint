package com.klk.mobilefingerprint.components;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.klk.mobilefingerprint.R;

public class ConfirmEnrollAlertDialog extends AlertDialog.Builder {

    private static final String TAG = ConfirmEnrollAlertDialog.class.getSimpleName();

    public ConfirmEnrollAlertDialog(Context context) {
        super(context);
    }

    @Override
    public AlertDialog create() {
        setPositiveButton(R.string.label_confirm_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                
            }
        });

        setNegativeButton(R.string.label_confirm_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        setMessage(R.string.label_message_enroll);
        setTitle(R.string.label_dialog_title);
        return super.create();
    }

}
