package com.psychologicalsituations.Utilits;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.psychologicalsituations.Helpers.LocalHelper;

import java.util.Locale;

public final class LanguageUtilities {

    //region language
    public static void changeLanguage(Activity activity, String language) {
        LocalHelper.setLocale(activity, language);
        Locale myLocale = new Locale(language);
        Resources res = activity.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        activity.recreate();
    }
    //endregion
    private static boolean isLanguageCorrect(String language, Activity activity) {
        Locale current = activity.getResources().getConfiguration().locale;
        String currentLanguage = current.getLanguage();
        return currentLanguage.equals(language);
    }

    public static void setUpActivityLanguage(Activity activity,String wantedLanguage){
        if (!isLanguageCorrect(wantedLanguage,activity)) {
            LanguageUtilities.changeLanguage(activity, wantedLanguage);
        }
    }
}
