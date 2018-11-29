package com.datacomp.magicfinmart.location;

import android.location.Location;


public interface ILocationStateListener {

    void onLocationChanged(Location location);

    void onConnected();

    void onConnectionFailed();
}
