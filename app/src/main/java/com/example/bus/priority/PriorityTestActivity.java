package com.example.bus.priority;

import android.os.Bundle;
import android.util.Log;

import com.example.bus.JavaBean;
import com.example.bus.R;
import com.example.bus.cody.TestScopeBus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cody.bus.ObserverWrapper;

public class PriorityTestActivity extends AppCompatActivity {

    private ObserverWrapper<JavaBean> observePriority2Forever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority);
        findViewById(R.id.send).setOnClickListener(view -> TestScopeBus.eventBean().post(new JavaBean("默认值", 10)));
        findViewById(R.id.observePriority1).
                setOnClickListener(view ->
                {
                    Log.d("PriorityTestActivity", "observePriority1 click");
                    TestScopeBus.eventBean()
                            .observe(PriorityTestActivity.this, new ObserverWrapper<JavaBean>(1) {
                                @Override
                                public void onChanged(@Nullable final JavaBean value) {
                                    Log.d("PriorityTestActivity", "observePriority1 value = " + value);
                                    value.name = "change by observePriority1";
                                    value.age = 1;
                                }
                            });
                }

       );
        findViewById(R.id.observeStickyPriority2).
                setOnClickListener(view ->
                {
                    Log.d("PriorityTestActivity", "observeStickyPriority2 click");
                    TestScopeBus.eventBean()
                            .observe(PriorityTestActivity.this,
                                    new ObserverWrapper<JavaBean>(2, true) {
                                        @Override
                                        public void onChanged(@Nullable final JavaBean value) {
                                            Log.d("PriorityTestActivity", "observeStickyPriority2 value = " + value);
                                            value.name = "change by observeStickyPriority2";
                                            value.age = 2;
                                        }
                                    });
                });
        findViewById(R.id.observePriority3).
                setOnClickListener(view ->
                {
                    Log.d("PriorityTestActivity", "observePriority3 click");
                    TestScopeBus.eventBean()
                            .observe(PriorityTestActivity.this,
                                    new ObserverWrapper<JavaBean>(3) {
                                        @Override
                                        public void onChanged(@Nullable final JavaBean value) {
                                            Log.d("PriorityTestActivity", "observePriority3 value = " + value);
                                            value.name = "change by observePriority3";
                                            value.age = 3;
                                        }
                                    });
                });
        findViewById(R.id.observePriorityF3).
                setOnClickListener(view ->
                {
                    Log.d("PriorityTestActivity", "observePriorityF3 click");
                    TestScopeBus.eventBean()
                            .observe(PriorityTestActivity.this, new ObserverWrapper<JavaBean>(-3) {
                                @Override
                                public void onChanged(@Nullable final JavaBean value) {
                                    Log.d("PriorityTestActivity", "observePriorityF3 value = " + value);
                                    value.name = "change by observePriorityF3";
                                    value.age = -3;
                                }
                            });
                });
        findViewById(R.id.observePriority2Forever).
                setOnClickListener(view ->
                {
                    Log.d("PriorityTestActivity", "observePriority2Forever click");
                    TestScopeBus.eventBean()
                            .observeForever(
                                    observePriority2Forever = new ObserverWrapper<JavaBean>(2) {
                                        @Override
                                        public void onChanged(@Nullable final JavaBean value) {
                                            Log.d("PriorityTestActivity", "observePriority2Forever value = " + value);
                                            value.name = "change by observePriority2Forever";
                                            value.age = 2000;
                                        }
                                    });
                });
        findViewById(R.id.removePriority2Forever).
                setOnClickListener(view ->
                {
                    Log.d("PriorityTestActivity", "removePriority2Forever click");
                    TestScopeBus.eventBean()
                            .removeObserver(observePriority2Forever);
                });
        findViewById(R.id.removeOwner).
                setOnClickListener(view ->
                {
                    Log.d("PriorityTestActivity", "removeOwner click");
                    TestScopeBus.eventBean()
                            .removeObservers(PriorityTestActivity.this);
                });

    }
}
