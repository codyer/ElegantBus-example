package com.example.bus;

import android.Manifest;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Map;

import cody.bus.ElegantBus;
import cody.bus.ObserverWrapper;


public class BaseActivity extends AppCompatActivity {

    private ObserverWrapper<Integer> observerWrapperInt;
    private ObserverWrapper<String> observerWrapperString;
    private ObserverWrapper<JavaBean> observerWrapperBean;
    private ObserverWrapper<List<String>> observerWrapperList;
    private ObserverWrapper<Map<String, List<String>>> observerWrapperMap;
    private ObserverWrapper<Object> observerWrapperDefault;

    public String getPage() {
        return "页面";
    }

    public Class<?> getNextPage() {
        return Main1Activity.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //申请权限
        int REQUEST_CODE_CONTACT = 101;
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
//验证是否申请权限
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationContext().checkSelfPermission(permissions[1]) != PackageManager.PERMISSION_GRANTED) {
                //申请权限
                ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_CONTACT);
            }
        }*/
        setContentView(R.layout.activity_page);
        TestUtil.log("进入页面：" + getPage());
        ((TextView) findViewById(R.id.page)).setText(
                String.format(getString(R.string.title_str), hashCode(), ElegantBus.getProcessName(), getPage()));
        findViewById(R.id.open).setOnClickListener(view -> TestUtil.open(this, getNextPage()));
        findViewById(R.id.testInt).setOnClickListener(view -> TestUtil.postInt());
        findViewById(R.id.testString).setOnClickListener(view -> TestUtil.postString());
        findViewById(R.id.testBean).setOnClickListener(view -> TestUtil.postBean());
        findViewById(R.id.testResetSticky).setOnClickListener(view -> TestUtil.resetSticky());
        findViewById(R.id.testList).setOnClickListener(view -> TestUtil.postList());
        findViewById(R.id.testMap).setOnClickListener(view -> TestUtil.postMap());
        findViewById(R.id.testDefault).setOnClickListener(view -> TestUtil.postDefault());

        TextView log = findViewById(R.id.log);
        observerWrapperInt = TestUtil.testInt(this, getPage(), log);
        observerWrapperString = TestUtil.testString(this, getPage(), log);
        observerWrapperBean = TestUtil.testBean(this, getPage(), log);
        observerWrapperList = TestUtil.testList(this, getPage(), log);
        observerWrapperMap = TestUtil.testMap(this, getPage(), log);
        observerWrapperDefault = TestUtil.testDefault(this, getPage(), log);
    }

    @Override
    protected void onDestroy() {
        TestUtil.removeInt(observerWrapperInt);
        TestUtil.removeString(observerWrapperString);
        TestUtil.removeBean(observerWrapperBean);
        TestUtil.removeList(observerWrapperList);
        TestUtil.removeMap(observerWrapperMap);
        TestUtil.removeDefault(observerWrapperDefault);
        super.onDestroy();
    }
}
