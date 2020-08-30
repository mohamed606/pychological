package com.psychologicalsituations.Utilits;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateUtilities {

    public static String formatDate(Date date, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm",locale);
        return simpleDateFormat.format(date);
    }
}
