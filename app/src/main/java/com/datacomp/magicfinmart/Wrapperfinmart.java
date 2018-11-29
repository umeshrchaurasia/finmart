package com.datacomp.magicfinmart;

import android.content.Context;
import android.content.Intent;

import com.datacomp.magicfinmart.splashscreen.SplashScreenActivity;

import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;

/**
 * Created by IN-RB on 29-11-2018.
 */

public class Wrapperfinmart {

    PrefManager prefManager;
    Context context;

    public Wrapperfinmart(Context context) {
        this.context = context;
        prefManager = new PrefManager(context);
    }

    public void initFinmart(int iTheme) {
        prefManager.setTheme(iTheme);
        context.startActivity(new Intent(context, SplashScreenActivity.class));

    }
}
