package com.klk.mobilefingerprint.components;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.klk.mobilefingerprint.R;

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
        View view = layoutInflater.inflate(R.layout.enroll_fingerprint, null);
        setView(view);

        setPositiveButton(R.string.label_enroll_finish, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mConfirmFinishEnrollDialog = new ConfirmFinishEnrollDialog(mId, 10, mContext);
                final AlertDialog alertDialog = mConfirmFinishEnrollDialog.create();
                alertDialog.show();
            }
        });

        setNegativeButton(R.string.label_enroll_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        setCancelable(false);
        return super.create();
    }
}
