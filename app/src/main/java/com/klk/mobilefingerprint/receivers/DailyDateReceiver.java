package com.klk.mobilefingerprint.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.klk.mobilefingerprint.utils.DateHelper;

import java.util.Calendar;
import java.util.Date;

public class DailyDateReceiver extends BroadcastReceiver{

    private static final String TAG = DailyDateReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Hai", Toast.LENGTH_SHORT).show();
    }
}
