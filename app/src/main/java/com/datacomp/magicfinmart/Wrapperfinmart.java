package com.datacomp.magicfinmart;

import android.content.Context;
import android.content.Intent;

import com.datacomp.magicfinmart.splashscreen.SplashScreenActivity;

/**
 * Created by IN-RB on 29-11-2018.
 */

public class Wrapperfinmart {

    public void initFinmart(Context context,int iTheme){

        context.startActivity(new Intent(context, SplashScreenActivity.class));

    }
}
