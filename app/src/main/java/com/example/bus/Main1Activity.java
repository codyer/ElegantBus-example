package com.example.bus;

import android.os.Bundle;

import cody.bus.ElegantBusX;
import cody.bus.ElegantLog;

public class Main1Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ElegantBusX.fixHighLevelAndroid(this, () -> {
            ElegantLog.e("you should accept the request !");
            return true;
        });
    }

    public String getPage() {
        return super.getPage() + "1";
    }

    public Class<?> getNextPage() {
        return Main2Activity.class;
    }
}
