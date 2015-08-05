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
    switch (event.getTaskStatus()) {
      case TASK_RUNNING:
        LOG.info("task '{}' running on ports '{}' under '{}'", event.getAppId(), event.getPorts(), event.getHost());
        break;
      case TASK_KILLED:
        LOG.info("task '{}' killed", event.getAppId());
        break;
      default:
        LOG.info("ignored status update event for '{}' since its status is '{}'",
                 event.getAppId(),
                 event.getTaskStatus());
    }
  }

}
