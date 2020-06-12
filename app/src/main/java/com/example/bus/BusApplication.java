package com.example.bus;

import android.app.Application;

import com.cody.component.bus.LiveEventBus;

public class BusApplication extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        LiveEventBus.supportMultiProcess(this,"com.example.bus");
    }

    @Override
    public void onTerminate() {
        LiveEventBus.stopSupportMultiProcess();
        super.onTerminate();
    }
}
