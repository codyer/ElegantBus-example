package com.example.bus;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.bus.cody.TestScopeBus;

import androidx.lifecycle.LifecycleOwner;
import cody.bus.ObserverWrapper;

public class TestUtil {
    private static String Normal = "Normal";
    private static String Sticky = "Sticky";
    private static String Forever = "Forever";
    private static int count = 0;

    public static void postInt() {
        TestScopeBus.eventInt().post((10 + count++));
    }

    public static void postString() {
        TestScopeBus.eventString().post("字符串" + (count++));
    }

    public static void postBean() {
        TestScopeBus.eventBean().post(new JavaBean("测试", count++));
    }

    public static ObserverWrapper<Integer> testInt(LifecycleOwner owner, String page) {
        TestScopeBus.eventInt().observe(owner, new ObserverWrapper<Integer>() {
            @Override
            public void onChanged(final Integer value) {
                log(Normal, value, page);
            }
        });
        TestScopeBus.eventInt().observe(owner, new ObserverWrapper<Integer>(true) {
            @Override
            public void onChanged(final Integer value) {
                log(Sticky, value, page);
            }
        });
        ObserverWrapper<Integer> observerWrapper;
        TestScopeBus.eventInt().observeForever(observerWrapper = new ObserverWrapper<Integer>(true) {
            @Override
            public void onChanged(final Integer value) {
                log(Forever, value, page);
            }
        });
        return observerWrapper;
    }

    public static ObserverWrapper<String> testString(LifecycleOwner owner, String page) {
        TestScopeBus.eventString().observe(owner, new ObserverWrapper<String>() {
            @Override
            public void onChanged(final String value) {
                log(Normal, value, page);
            }
        });
        TestScopeBus.eventString().observe(owner, new ObserverWrapper<String>(true) {
            @Override
            public void onChanged(final String value) {
                log(Sticky, value, page);
            }
        });
        ObserverWrapper<String> observerWrapper;
        TestScopeBus.eventString().observeForever(observerWrapper = new ObserverWrapper<String>(true) {
            @Override
            public void onChanged(final String value) {
                log(Forever, value, page);
            }
        });
        return observerWrapper;
    }

    public static ObserverWrapper<JavaBean> testBean(LifecycleOwner owner, String page) {
        TestScopeBus.eventBean().observe(owner, new ObserverWrapper<JavaBean>() {
            @Override
            public void onChanged(final JavaBean value) {
                log(Normal, value, page);
            }
        });
        TestScopeBus.eventBean().observe(owner, new ObserverWrapper<JavaBean>(true) {
            @Override
            public void onChanged(final JavaBean value) {
                log(Sticky, value, page);
            }
        });
        ObserverWrapper<JavaBean> observerWrapper;
        TestScopeBus.eventBean().observeForever(observerWrapper = new ObserverWrapper<JavaBean>(true) {
            @Override
            public void onChanged(final JavaBean value) {
                log(Forever, value, page);
            }
        });
        return observerWrapper;
    }

    public static void open(Context context, Class<?> clz) {
        Intent intent = new Intent(context, clz);
        context.startActivity(intent);
    }

    public static void removeInt(ObserverWrapper<Integer> observerWrapper) {
        TestScopeBus.eventInt().removeObserver(observerWrapper);
    }

    public static void removeString(ObserverWrapper<String> observerWrapper) {
        TestScopeBus.eventString().removeObserver(observerWrapper);
    }

    public static void removeBean(ObserverWrapper<JavaBean> observerWrapper) {
        TestScopeBus.eventBean().removeObserver(observerWrapper);
    }

    public static void log(String type, Object value, String page) {
        Log.d("TestUtil", Application.getProcessName() + ":" + page + ":收到 " + type + " 消息(" + value.getClass().getCanonicalName() + "):" + value);
    }
}
