package com.klk.mobilefingerprint.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.klk.mobilefingerprint.utils.DateHelper;

import java.util.Calendar;
import java.util.Date;

public class DailyDateReceiver extends BroadcastReceiver{

    private static final String TAG = DailyDateReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        DateHelper dateHelper = new DateHelper();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();

        String nextDay = dateHelper.getNextDayOfDate(calendar, date);

        Toast.makeText(context, nextDay, Toast.LENGTH_LONG).show();
    }
}
