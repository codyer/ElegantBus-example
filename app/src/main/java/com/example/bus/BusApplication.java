package com.example.bus;

import android.app.Application;

import cody.bus.ElegantBusX;


public class BusApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ElegantBusX.supportMultiAppMultiProcess(this, "com.example.bus");
    }

    @Override
    public void onTerminate() {
        ElegantBusX.stopSupportMultiProcess();
        super.onTerminate();
    }
}
