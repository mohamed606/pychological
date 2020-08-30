package com.psychologicalsituations.Utilits;

import android.content.Context;
import android.content.res.Configuration;

public final class OrientationUtils {
    public static int getNumberOfColumns(Context context){
        int orientation = context.getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            return  3;
        }else{
            return  2;
        }
    }
}
