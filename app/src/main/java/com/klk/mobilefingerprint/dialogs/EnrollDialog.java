package com.klk.mobilefingerprint.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.constantvalues.EnrollingConfig;

public class EnrollDialog extends AlertDialog.Builder{

    private Context mContext;
    private int mId;
    private ConfirmFinishEnrollDialog mConfirmFinishEnrollDialog;

    public EnrollDialog(int id, Context context) {
        super(context);
        this.mId = id;
        this.mContext = context;
    }

    @Override
    public AlertDialog.Builder setView(View view) {
        return super.setView(view);
    }

    @Override
    public AlertDialog create() {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (layoutInflater != null) {
            View view = layoutInflater.inflate(R.layout.enroll_fingerprint, null);
            setView(view);
        }

        setPositiveButton(R.string.label_confirm_finish, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                mConfirmFinishEnrollDialog = new ConfirmFinishEnrollDialog(mId, EnrollingConfig.TOTAL_FINGER, mContext);
                final AlertDialog alertDialog = mConfirmFinishEnrollDialog.create();
                alertDialog.show();
            }
        });

        setNegativeButton(R.string.label_confirm_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        setCancelable(false);
        return super.create();
    }
}
