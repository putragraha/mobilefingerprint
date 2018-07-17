package com.klk.mobilefingerprint.components;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.klk.mobilefingerprint.R;

public class ConfirmEnrollAlertDialog extends AlertDialog.Builder {

    private static final String TAG = ConfirmEnrollAlertDialog.class.getSimpleName();

    private int mId;
    private String mName;
    private Context mContext;

    private EnrollDialog mEnrollDialog;

    public ConfirmEnrollAlertDialog(int id, String name, Context context) {
        super(context);
        this.mContext = context;
        this.mId = id;
        this.mName = name;
    }

    @Override
    public AlertDialog create() {
        setPositiveButton(R.string.label_confirm_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mEnrollDialog = new EnrollDialog(mContext);
                final AlertDialog alertDialog = mEnrollDialog.create();
                alertDialog.show();
            }
        });

        setNegativeButton(R.string.label_confirm_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        String msg = mContext.getResources().getString(R.string.label_message_enroll);
        setMessage(msg + " " + mName);
        setTitle(R.string.label_dialog_title);
        return super.create();
    }

}
