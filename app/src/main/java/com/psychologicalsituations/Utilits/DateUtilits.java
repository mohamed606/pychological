package com.psychologicalsituations.Utilits;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtilits {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    public static String fromatDate(Date date) {
        return simpleDateFormat.format(date);
    }
}
