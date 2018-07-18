package com.klk.mobilefingerprint.components;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.klk.mobilefingerprint.R;

public class ConfirmFinishEnrollDialog extends AlertDialog.Builder {

    private static final String TAG = ConfirmFinishEnrollDialog.class.getSimpleName();

    private int mId;
    private int mFingerTotal;
    private Context mContext;

    private EnrollDialog mEnrollDialog;

    public ConfirmFinishEnrollDialog(int id, int fingerTotal, Context context) {
        super(context);
        this.mContext = context;
        this.mId = id;
        this.mFingerTotal = fingerTotal;
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

        String msg = mContext.getResources().getString(R.string.label_message_finish_enroll);
        setMessage(mFingerTotal + " " + msg);
        setTitle(R.string.label_dialog_title);
        return super.create();
    }

}
