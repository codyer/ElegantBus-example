package com.example.bus;

public class Main1Activity extends BaseActivity {

    public String getPage() {
        return super.getPage() + "1";
    }

    public Class<?> getNextPage() {
        return Main2Activity.class;
    }
}
