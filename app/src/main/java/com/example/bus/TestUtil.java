package com.example.bus;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cody.component.bus.LiveEventBus;
import com.cody.component.bus.wrapper.ObserverWrapper;
import com.example.bus.event.Scope$TestScope;

import androidx.lifecycle.LifecycleOwner;

public class TestUtil {
    private static String Normal = "Normal";
    private static String Sticky = "Sticky";
    private static String Forever = "Forever";
    private static int count = 0;

    public static void postInt() {
        LiveEventBus.begin().inScope(Scope$TestScope.class).eventInt().post((10+count++));
    }

    public static void postString() {
        LiveEventBus.begin().inScope(Scope$TestScope.class).eventString().post("字符串" + (count++));
    }

    public static void postBean() {
        LiveEventBus.begin().inScope(Scope$TestScope.class).eventBean().post(new JavaBean("测试", count++));
    }

    public static ObserverWrapper<Integer> testInt(LifecycleOwner owner) {
        LiveEventBus.begin().inScope(Scope$TestScope.class).eventInt().observe(owner, new ObserverWrapper<Integer>() {
            @Override
            public void onChanged(final Integer value) {
                log(Normal, value);
            }
        });
        LiveEventBus.begin().inScope(Scope$TestScope.class).eventInt().observe(owner, new ObserverWrapper<Integer>(true) {
            @Override
            public void onChanged(final Integer value) {
                log(Sticky, value);
            }
        });
        ObserverWrapper<Integer> observerWrapper;
        LiveEventBus.begin().inScope(Scope$TestScope.class).eventInt().observeForever(observerWrapper = new ObserverWrapper<Integer>(true) {
            @Override
            public void onChanged(final Integer value) {
                log(Forever, value);
            }
        });
        return observerWrapper;
    }

    public static ObserverWrapper<String> testString(LifecycleOwner owner) {
        LiveEventBus.begin().inScope(Scope$TestScope.class).eventString().observe(owner, new ObserverWrapper<String>() {
            @Override
            public void onChanged(final String value) {
                log(Normal, value);
            }
        });
        LiveEventBus.begin().inScope(Scope$TestScope.class).eventString().observe(owner, new ObserverWrapper<String>(true) {
            @Override
            public void onChanged(final String value) {
                log(Sticky, value);
            }
        });
        ObserverWrapper<String> observerWrapper;
        LiveEventBus.begin().inScope(Scope$TestScope.class).eventString().observeForever(observerWrapper = new ObserverWrapper<String>(true) {
            @Override
            public void onChanged(final String value) {
                log(Forever, value);
            }
        });
        return observerWrapper;
    }

    public static ObserverWrapper<JavaBean> testBean(LifecycleOwner owner) {
        LiveEventBus.begin().inScope(Scope$TestScope.class).eventBean().observe(owner, new ObserverWrapper<JavaBean>() {
            @Override
            public void onChanged(final JavaBean value) {
                log(Normal, value);
            }
        });
        LiveEventBus.begin().inScope(Scope$TestScope.class).eventBean().observe(owner, new ObserverWrapper<JavaBean>(true) {
            @Override
            public void onChanged(final JavaBean value) {
                log(Sticky, value);
            }
        });
        ObserverWrapper<JavaBean> observerWrapper;
        LiveEventBus.begin().inScope(Scope$TestScope.class).eventBean().observeForever(observerWrapper = new ObserverWrapper<JavaBean>(true) {
            @Override
            public void onChanged(final JavaBean value) {
                log(Forever, value);
            }
        });
        return observerWrapper;
    }

    public static void open(Context context, Class<?> clz) {
        Intent intent = new Intent(context, clz);
        context.startActivity(intent);
    }

    public static void removeInt(ObserverWrapper<Integer> observerWrapper) {
        LiveEventBus.begin().inScope(Scope$TestScope.class).eventInt().removeObserver(observerWrapper);
    }

    public static void removeString(ObserverWrapper<String> observerWrapper) {
        LiveEventBus.begin().inScope(Scope$TestScope.class).eventString().removeObserver(observerWrapper);
    }

    public static void removeBean(ObserverWrapper<JavaBean> observerWrapper) {
        LiveEventBus.begin().inScope(Scope$TestScope.class).eventBean().removeObserver(observerWrapper);
    }

    public static void log(String type, Object value) {
        Log.d("TestUtil", Application.getProcessName() + ":收到 " + type + " 消息(" + value.getClass().getCanonicalName() + "):" + value);
    }
}
