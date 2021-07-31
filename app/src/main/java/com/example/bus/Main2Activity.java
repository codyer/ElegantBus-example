package com.example.bus;

public class Main2Activity extends BaseActivity {
    @Override
    public String getPage() {
        return "页面2";
    }

    @Override
    public Class<?> getNextPage() {
        return Main3Activity.class;
    }
}
