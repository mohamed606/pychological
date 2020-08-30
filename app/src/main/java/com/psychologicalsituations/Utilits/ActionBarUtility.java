package com.psychologicalsituations.Utilits;

import androidx.appcompat.app.ActionBar;

public final class ActionBarUtility {
    public static void setUpButton(ActionBar actionBar, int indicatorId){
        if(actionBar != null){
            actionBar.setHomeAsUpIndicator(indicatorId);
        }
    }
}
