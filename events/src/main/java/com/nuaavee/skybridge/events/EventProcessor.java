package com.nuaavee.skybridge.events;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.collect.Maps;
import com.nuaavee.skybridge.events.listener.EventListener;
import com.nuaavee.skybridge.events.listener.EventListenerType;
import com.nuaavee.skybridge.events.type.MarathonEvent;

@Component
public class EventProcessor {

  private static final Logger LOG = LoggerFactory.getLogger(EventProcessor.class);

  private Map<EventType, EventListener> eventListenerMap = Maps.newHashMap();

  @Autowired
  public EventProcessor(List<EventListener> eventListeners) {
    for (EventListener eventListener : eventListeners) {
      EventType eventType = eventListener.getClass().getAnnotation(EventListenerType.class).value();
      eventListenerMap.put(eventType, eventListener);
    }
  }

  @SuppressWarnings("unchecked") // event_type and listener types are linked manually
  public boolean process(MarathonEvent event) {
    if (!eventListenerMap.containsKey(event.getEventType())) {
      LOG.warn("no event processor defined for event " + event.getEventType());
      return false;
    }
    EventListener listener = eventListenerMap.get(event.getEventType());
    LOG.info("processing event " + event.getEventType() + " using " + listener.getClass().getCanonicalName());
    listener.process(event);
    return true;
  }
}
