package com.example.bus;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import cody.bus.ElegantBus;
import cody.bus.ElegantLog;
import cody.bus.ObserverWrapper;

public class Main1Activity extends AppCompatActivity {

    private ObserverWrapper<Integer> observerWrapperInt;
    private ObserverWrapper<String> observerWrapperString;
    private ObserverWrapper<JavaBean> observerWrapperBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        ((TextView) findViewById(R.id.page)).setText("页面1");
        findViewById(R.id.open).setOnClickListener(view -> TestUtil.open(this, Main2Activity.class));
        findViewById(R.id.testInt).setOnClickListener(view -> TestUtil.postInt());
        findViewById(R.id.testString).setOnClickListener(view -> TestUtil.postString());
        findViewById(R.id.testBean).setOnClickListener(view -> TestUtil.postBean());
        findViewById(R.id.testDefault).setOnClickListener(view ->
                {
                    ElegantBus.getDefault("EventA").post(new JavaBean("eventA", 10));
                    ElegantBus.getDefault("EventA").post("eventA");
                    ElegantBus.getDefault("EventA").post(888888);
                }
        );
        observerWrapperInt = TestUtil.testInt(this, "页面1");
        observerWrapperString = TestUtil.testString(this, "页面1");
        observerWrapperBean = TestUtil.testBean(this, "页面1");
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

    @Override
    protected void onDestroy() {
        TestUtil.removeInt(observerWrapperInt);
        TestUtil.removeString(observerWrapperString);
        TestUtil.removeBean(observerWrapperBean);
        super.onDestroy();
    }
}
