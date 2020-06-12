package com.example.bus;

public class JavaBean {
    public String name;
    public int age;

    public JavaBean() {
    }

    public JavaBean(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
