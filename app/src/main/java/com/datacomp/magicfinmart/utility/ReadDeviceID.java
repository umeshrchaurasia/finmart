package com.datacomp.magicfinmart.utility;

import android.app.Activity;
import android.provider.Settings;

public class ReadDeviceID {
    Activity activityRegister;

    public ReadDeviceID(Activity actReg) {
        activityRegister = (Activity) actReg;
    }

    public String getAndroidID() {
        if (activityRegister != null) {
            return Settings.Secure.getString(activityRegister.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        }
        return "";

    }
}