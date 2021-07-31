package com.example.bus;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bus.cody.TestScopeBus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cody.bus.ObserverWrapper;


public class Main5Activity extends AppCompatActivity {
    private static int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
        ((TextView) findViewById(R.id.page)).setText("页面5");
        findViewById(R.id.send).setOnClickListener(view -> TestScopeBus.eventString().post("A" + (i++)));
        findViewById(R.id.observe).
                setOnClickListener(view ->
                TestScopeBus.eventString().observeSticky(Main5Activity.this, new ObserverWrapper<String>() {
                    @Override
                    public void onChanged(@Nullable final String value) {
                        Log.d("Main5Activity","value = " + value);
                        Toast.makeText(Main5Activity.this, value, Toast.LENGTH_LONG).show();
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
