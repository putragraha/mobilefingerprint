package com.klk.mobilefingerprint.services;

import java.util.Calendar;
import java.util.Date;

public interface DateControl {
    String getDateText(Date date);
    String getNextDayOfDate(Calendar calendar, Date date);
}
