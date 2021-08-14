package com.example.bus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.example.bus.cody.TestScopeBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.LifecycleOwner;
import cody.bus.ElegantBus;
import cody.bus.ObserverWrapper;

class TestUtil {
    private static final String Normal = "Normal";
    private static final String Sticky = "Sticky";
    private static final String Forever = "Forever";
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat Format = new SimpleDateFormat("HH:mm:ss");
    private static int count = 0;

    static void postInt() {
        TestScopeBus.eventInt().post((10 + count++));
    }

    static void postString() {
        TestScopeBus.eventString().post("字符串" + (count++));
    }

    static void postBean() {
        TestScopeBus.eventBean().post(new JavaBean("测试", count++));
    }

    static void postList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("测试" + count++);
        list.add("测试" + count++);
        TestScopeBus.eventList().post(list);
    }

    static void postMap() {
        ArrayList<String> list = new ArrayList<>();
        list.add("测试" + count++);
        list.add("测试" + count++);
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("测试" + count++, list);
        TestScopeBus.eventMap().post(map);
    }

    static ObserverWrapper<Integer> testInt(LifecycleOwner owner, String page, final TextView log) {
        TestScopeBus.eventInt().observe(owner, new ObserverWrapper<Integer>() {
            @Override
            public void onChanged(final Integer value) {
                log(Normal, value, page, log);
            }
        });
        TestScopeBus.eventInt().observe(owner, new ObserverWrapper<Integer>(true) {
            @Override
            public void onChanged(final Integer value) {
                log(Sticky, value, page, log);
            }
        });
        ObserverWrapper<Integer> observerWrapper;
        TestScopeBus.eventInt().observeForever(observerWrapper = new ObserverWrapper<Integer>() {
            @Override
            public void onChanged(final Integer value) {
                log(Forever, value, page, log);
            }
        });
        return observerWrapper;
    }

    static ObserverWrapper<String> testString(LifecycleOwner owner, String page, final TextView log) {
        TestScopeBus.eventString().observe(owner, new ObserverWrapper<String>() {
            @Override
            public void onChanged(final String value) {
                log(Normal, value, page, log);
            }
        });
        TestScopeBus.eventString().observe(owner, new ObserverWrapper<String>(true) {
            @Override
            public void onChanged(final String value) {
                log(Sticky, value, page, log);
            }
        });
        ObserverWrapper<String> observerWrapper;
        TestScopeBus.eventString().observeForever(observerWrapper = new ObserverWrapper<String>(true) {
            @Override
            public void onChanged(final String value) {
                log(Forever, value, page, log);
            }
        });
        return observerWrapper;
    }

    static ObserverWrapper<JavaBean> testBean(LifecycleOwner owner, String page, final TextView log) {
        TestScopeBus.eventBean().observe(owner, new ObserverWrapper<JavaBean>() {
            @Override
            public void onChanged(final JavaBean value) {
                log(Normal, value, page, log);
            }
        });
        TestScopeBus.eventBean().observe(owner, new ObserverWrapper<JavaBean>(true) {
            @Override
            public void onChanged(final JavaBean value) {
                log(Sticky, value, page, log);
            }
        });
        ObserverWrapper<JavaBean> observerWrapper;
        TestScopeBus.eventBean().observeForever(observerWrapper = new ObserverWrapper<JavaBean>() {
            @Override
            public void onChanged(final JavaBean value) {
                log(Forever, value, page, log);
            }
        });
        return observerWrapper;
    }

    static ObserverWrapper<List<String>> testList(LifecycleOwner owner, String page, final TextView log) {
        TestScopeBus.eventList().observe(owner, new ObserverWrapper<List<String>>() {
            @Override
            public void onChanged(final List<String> value) {
                log(Normal, value, page, log);
            }
        });
        TestScopeBus.eventList().observe(owner, new ObserverWrapper<List<String>>(true) {
            @Override
            public void onChanged(final List<String> value) {
                log(Sticky, value, page, log);
            }
        });
        ObserverWrapper<List<String>> observerWrapper;
        TestScopeBus.eventList().observeForever(observerWrapper = new ObserverWrapper<List<String>>() {
            @Override
            public void onChanged(final List<String> value) {
                log(Forever, value, page, log);
            }
        });
        return observerWrapper;
    }

    static ObserverWrapper<Map<String, List<String>>> testMap(LifecycleOwner owner, String page, final TextView log) {
        TestScopeBus.eventMap().observe(owner, new ObserverWrapper<Map<String, List<String>>>() {
            @Override
            public void onChanged(final Map<String, List<String>> value) {
                log(Normal, value, page, log);
            }
        });
        TestScopeBus.eventMap().observe(owner, new ObserverWrapper<Map<String, List<String>>>(true) {
            @Override
            public void onChanged(final Map<String, List<String>> value) {
                log(Sticky, value, page, log);
            }
        });
        ObserverWrapper<Map<String, List<String>>> observerWrapper;
        TestScopeBus.eventMap().observeForever(observerWrapper = new ObserverWrapper<Map<String, List<String>>>() {
            @Override
            public void onChanged(final Map<String, List<String>> value) {
                log(Forever, value, page, log);
            }
        });
        return observerWrapper;
    }

    static void open(Context context, Class<?> clz) {
        Intent intent = new Intent(context, clz);
        context.startActivity(intent);
    }

    static void removeInt(ObserverWrapper<Integer> observerWrapper) {
        TestScopeBus.eventInt().removeObserver(observerWrapper);
    }

    static void removeString(ObserverWrapper<String> observerWrapper) {
        TestScopeBus.eventString().removeObserver(observerWrapper);
    }

    static void removeBean(ObserverWrapper<JavaBean> observerWrapper) {
        TestScopeBus.eventBean().removeObserver(observerWrapper);
    }

    static void removeList(ObserverWrapper<List<String>> observerWrapper) {
        TestScopeBus.eventList().removeObserver(observerWrapper);
    }

    static void removeMap(ObserverWrapper<Map<String, List<String>>> observerWrapper) {
        TestScopeBus.eventMap().removeObserver(observerWrapper);
    }

    private static void log(String type, Object value, String page, final TextView log) {
        String out = ElegantBus.getProcessName() + ":" + page + ":收到 " + type + " 消息(" + value.getClass().getCanonicalName() + "):" + value;
        if (log != null) {
            log.post(() -> {
                if (log != null) {
                    log.setText(String.format("%s \n %s : %s", log.getText(), Format.format(new Date()), out));
                }
            });
        }
        log(out);
    }

    public static void log(String log) {
        Log.d("TestUtil_ElegantBus", log);
    }

    public static void resetSticky() {
        TestScopeBus.eventInt().resetSticky();
        TestScopeBus.eventMap().resetSticky();
        TestScopeBus.eventList().resetSticky();
        TestScopeBus.eventBean().resetSticky();
        TestScopeBus.eventString().resetSticky();
    }
}
