package com.klk.mobilefingerprint.components;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;

import com.klk.mobilefingerprint.constantvalues.Timer;
import com.klk.mobilefingerprint.utils.TimeWriter;

import java.util.Date;

public class AttendanceDialog {

    private TimeWriter mTimeWriter = new TimeWriter();
    private Context mContext;

    public AttendanceDialog(Context context){
        this.mContext = context;
    }

    public void call() {
        SuccessAttendanceDialog builder = new SuccessAttendanceDialog(mContext);

        Date date = new Date();
        String currTime = mTimeWriter.getText(date);
        builder.setCurrTime(currTime);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        SuccessWindowManager layoutParams = new SuccessWindowManager(mContext);
        alertDialog.getWindow().setAttributes(layoutParams);

        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }
            }
        };

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, Timer.ATTENDANCE_SUCCESS_DIALOG);
    }
}
