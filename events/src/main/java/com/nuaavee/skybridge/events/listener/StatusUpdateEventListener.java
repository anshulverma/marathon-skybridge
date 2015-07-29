package com.nuaavee.skybridge.events.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.nuaavee.skybridge.events.type.StatusUpdateEvent;
import static com.nuaavee.skybridge.events.EventType.STATUS_UPDATE;

@Component
@EventListenerType(STATUS_UPDATE)
public class StatusUpdateEventListener implements EventListener<StatusUpdateEvent> {

  private static final Logger LOG = LoggerFactory.getLogger(StatusUpdateEventListener.class);

  @Override
  public void process(StatusUpdateEvent event) {
    LOG.info("handling " + event.getEventType());
  }

}
