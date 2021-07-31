package com.example.bus;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import cody.bus.ObserverWrapper;


public class BaseActivity extends AppCompatActivity {

    private ObserverWrapper<Integer> observerWrapperInt;
    private ObserverWrapper<String> observerWrapperString;
    private ObserverWrapper<JavaBean> observerWrapperBean;
    private ObserverWrapper<List<String>> observerWrapperList;
    private ObserverWrapper<Map<String, List<String>>> observerWrapperMap;

    public String getPage() {
        return "页面4";
    }

    public Class<?> getNextPage() {
        return Main1Activity.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        ((TextView) findViewById(R.id.page)).setText(getPage());
        findViewById(R.id.open).setOnClickListener(view -> TestUtil.open(this, getNextPage()));
        findViewById(R.id.testInt).setOnClickListener(view -> TestUtil.postInt());
        findViewById(R.id.testString).setOnClickListener(view -> TestUtil.postString());
        findViewById(R.id.testBean).setOnClickListener(view -> TestUtil.postBean());
        findViewById(R.id.testList).setOnClickListener(view -> TestUtil.postList());
        findViewById(R.id.testMap).setOnClickListener(view -> TestUtil.postMap());
        observerWrapperInt = TestUtil.testInt(this, getPage());
        observerWrapperString = TestUtil.testString(this, getPage());
        observerWrapperBean = TestUtil.testBean(this, getPage());
        observerWrapperList = TestUtil.testList(this, getPage());
        observerWrapperMap = TestUtil.testMap(this, getPage());
    }

    @Override
    protected void onDestroy() {
        TestUtil.removeInt(observerWrapperInt);
        TestUtil.removeString(observerWrapperString);
        TestUtil.removeBean(observerWrapperBean);
        TestUtil.removeList(observerWrapperList);
        TestUtil.removeMap(observerWrapperMap);
        super.onDestroy();
    }
}
