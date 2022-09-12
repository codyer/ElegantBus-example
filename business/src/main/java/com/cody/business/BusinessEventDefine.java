package com.cody.business;


import java.util.List;
import java.util.Map;

import cody.bus.annotation.Event;
import cody.bus.annotation.EventGroup;

@EventGroup(value = "TestBusinessScope", active = true)
class BusinessEventDefine {
    @Event(value = "eventInt 事件测试", multiProcess = false)
    Integer eventInt;

    @Event(value = "eventString 事件测试", multiProcess = true)
    String eventString;

    @Event(value = "eventBean 事件测试", multiProcess = true)
    JavaBean eventBean;

    @Event(value = "eventBean 事件测试", multiProcess = true)
    List<String> eventList;

    @Event(value = "eventMap 泛型测试", multiProcess = true)
    Map<String, List<String>> eventMap;
}
