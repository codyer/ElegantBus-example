package com.example.bus;

import com.cody.component.bus.lib.annotation.Event;
import com.cody.component.bus.lib.annotation.EventScope;

@EventScope(value = "TestScope2", active = false)
public enum EventDefine2 {
    @Event(value = "event1", data = Integer.class, process = false) eventInt,
    @Event(value = "event1", data = String.class, process = true) eventString,
    @Event(value = "event2", data = String.class, process = true) eventString2,
    @Event(value = "event2", data = JavaBean.class, process = true) eventBean,
}
