package com.psychologicalsituations.Applications;

import android.app.Application;
import android.content.Context;

import com.psychologicalsituations.Helpers.LocalHelper;
import com.psychologicalsituations.R;

public class MainApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocalHelper.onAttach(base,"en"));
    }
}
