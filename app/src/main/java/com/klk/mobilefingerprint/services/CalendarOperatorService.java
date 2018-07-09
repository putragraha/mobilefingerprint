package com.klk.mobilefingerprint.services;

import java.util.Calendar;
import java.util.Date;

public interface CalendarOperatorService {
    void setCalendar(Calendar calendar, int hour, int minute, int second);
    Date addDate(Calendar calendar, Date date, int increment);
}
