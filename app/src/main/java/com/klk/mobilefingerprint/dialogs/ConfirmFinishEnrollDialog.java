package com.klk.mobilefingerprint.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.klk.mobilefingerprint.R;

public class ConfirmFinishEnrollDialog extends AlertDialog.Builder {

    private int mId;
    private int mFingerTotal;
    private Context mContext;

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
                // TODO : Update DB
                dialogInterface.dismiss();
            }
        });

        setNegativeButton(R.string.label_confirm_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        String msg = mContext.getResources().getString(R.string.label_message_finish_enroll);
        setMessage(mFingerTotal + " " + msg);
        setTitle(R.string.label_dialog_title);
        return super.create();
    }

}
