package com.klk.mobilefingerprint.services;

import android.content.Context;

import java.util.Calendar;
import java.util.Date;

public interface DateWriterService {
    String getDateText(Date date);
    String getDayOfDate(Context context, Calendar calendar);
}
