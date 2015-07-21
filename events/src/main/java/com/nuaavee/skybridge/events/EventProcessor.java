package com.nuaavee.skybridge.events;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nuaavee.skybridge.events.listener.EventListener;
import com.nuaavee.skybridge.events.type.MarathonEvent;

@Component
public class EventProcessor {

  @Autowired
  private List<EventListener> eventListeners;

  public void process(MarathonEvent event) {

  }
}
