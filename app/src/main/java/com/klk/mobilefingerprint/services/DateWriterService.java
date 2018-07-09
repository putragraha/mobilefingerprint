package com.klk.mobilefingerprint.services;

import java.util.Calendar;
import java.util.Date;

public interface DateWriterService {
    String getDateText(Date date);
    String getDayOfDate(Calendar calendar);
}
