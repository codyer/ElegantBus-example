package com.example.bus;

import com.cody.component.bus.lib.annotation.Event;
import com.cody.component.bus.lib.annotation.EventScope;

@EventScope(value = "TestScope", active = true)
public enum EventDefine {
    @Event(value = "eventInt 事件测试", data = Integer.class, process = false) eventInt,
    @Event(value = "eventString 事件测试", data = String.class, process = true) eventString,
    @Event(value = "eventBean 事件测试", data = JavaBean.class, process = true) eventBean,
}
