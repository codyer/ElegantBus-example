package com.example.bus;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import cody.bus.ElegantBus;
import cody.bus.ElegantLog;
import cody.bus.ObserverWrapper;

public class Main1Activity extends BaseActivity {

    public String getPage() {
        return "页面1";
    }

    public Class<?> getNextPage() {
        return Main2Activity.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.testDefault).setOnClickListener(view ->
                {
                    ElegantBus.getDefault("EventA").post(new JavaBean("eventA", 10));
                    ElegantBus.getDefault("EventA").post("eventA");
                    ElegantBus.getDefault("EventA").post(888888);
                }
        );
        ElegantBus.getDefault("EventA").observeSticky(this, new ObserverWrapper<Object>() {
            @Override
            public void onChanged(final Object value) {
                ElegantLog.d(value.toString());
            }
        });
        ObserverWrapper<Object> foreverObserverWrapper;
        ElegantBus.getDefault("EventA").observeForever(foreverObserverWrapper = new ObserverWrapper<Object>() {
            @Override
            public void onChanged(final Object value) {
                ElegantLog.d(value.toString());
            }
        });
        ElegantBus.getDefault("EventA").removeObserver(foreverObserverWrapper);
        ElegantBus.getDefault("EventA").observe(this, new ObserverWrapper<Object>() {
            @Override
            public void onChanged(final Object value) {
                if (value instanceof String) {
                    ElegantLog.d("String:" + value.toString());
                }
            }
        });

    }
}
