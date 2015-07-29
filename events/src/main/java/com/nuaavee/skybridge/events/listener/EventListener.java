package com.nuaavee.skybridge.events.listener;

import com.nuaavee.skybridge.events.type.MarathonEvent;

public interface EventListener<T extends MarathonEvent> {

  void process(T event);

}
