package com.datacomp.magicfinmart.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;

/**
 * Created by Nilesh Birhade on 24-08-2018.
 */

public class UpdateAppBroadCastReceiver extends BroadcastReceiver {
    PrefManager manager;

    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            manager = new PrefManager(context);
            //mps info
            manager.setMPS(manager.getMps());
            //first launch
            manager.setFirstTimeLaunch(manager.isFirstTimeLaunch());
            //Posp info
            manager.setPospInformation(manager.getPospInformation());

            //DBPersistanceController db = new DBPersistanceController(context);

            //user data
           // db.storeUserData(db.getUserData());

        } catch (Exception e) {

        }
    }

}
