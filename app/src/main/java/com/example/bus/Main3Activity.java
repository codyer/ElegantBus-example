package com.example.bus;

public class Main3Activity extends BaseActivity {
    @Override
    public String getPage() {
        return "页面3";
    }

    @Override
    public Class<?> getNextPage() {
        return Main4Activity.class;
    }
}