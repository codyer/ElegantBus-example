package com.example.bus;

import android.app.Application;

import cody.bus.ElegantBus;
import cody.bus.ElegantBusX;


public class BusApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ElegantBus.setDebug(true);
        ElegantBusX.supportMultiProcess(this);
    }

    @Override
    public void onTerminate() {
        ElegantBusX.stopSupportMultiProcess();
        super.onTerminate();
    }
}
