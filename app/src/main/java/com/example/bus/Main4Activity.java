package com.example.bus;

public class Main4Activity extends BaseActivity {
    @Override
    public String getPage() {
        return super.getPage() + "4";
    }

    @Override
    public Class<?> getNextPage() {
        return Main1Activity.class;
    }
}
