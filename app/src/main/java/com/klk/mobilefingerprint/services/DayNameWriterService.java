package com.klk.mobilefingerprint.services;

import android.content.Context;

import java.util.Calendar;

public interface DayNameWriterService {
    String getDayOfDate(Context context, Calendar calendar);
}
