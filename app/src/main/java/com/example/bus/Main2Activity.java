package com.example.bus;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import cody.bus.ObserverWrapper;


public class Main2Activity extends AppCompatActivity {

    private ObserverWrapper<Integer> observerWrapperInt;
    private ObserverWrapper<String> observerWrapperString;
    private ObserverWrapper<JavaBean> observerWrapperBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        ((TextView) findViewById(R.id.page)).setText("页面2");
        findViewById(R.id.open).setOnClickListener(view -> TestUtil.open(this, Main3Activity.class));
        findViewById(R.id.testInt).setOnClickListener(view -> TestUtil.postInt());
        findViewById(R.id.testString).setOnClickListener(view -> TestUtil.postString());
        findViewById(R.id.testBean).setOnClickListener(view -> TestUtil.postBean());
        observerWrapperInt = TestUtil.testInt(this, "页面2");
        observerWrapperString = TestUtil.testString(this, "页面2");
        observerWrapperBean = TestUtil.testBean(this, "页面2");
    }

    @Override
    protected void onDestroy() {
        TestUtil.removeInt(observerWrapperInt);
        TestUtil.removeString(observerWrapperString);
        TestUtil.removeBean(observerWrapperBean);
        super.onDestroy();
    }
}
