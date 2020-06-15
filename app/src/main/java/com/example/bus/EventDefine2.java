package com.example.bus;


import cody.bus.annotation.Event;
import cody.bus.annotation.EventGroup;

@EventGroup(value = "TestScope2", active = true)
public class EventDefine2 {
    @Event(value = "eventInt 事件测试", multiProcess = false)
    Integer eventInt;

    @Event(value = "eventString 事件测试", multiProcess = true)
    String eventString;

    @Event(value = "eventString 事件测试", multiProcess = true)
    String eventString2;

    @Event(value = "eventBean 事件测试", multiProcess = true)
    JavaBean eventBean;
}
