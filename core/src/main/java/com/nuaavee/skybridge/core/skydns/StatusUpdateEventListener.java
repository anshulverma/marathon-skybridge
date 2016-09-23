package com.nuaavee.skybridge.core.skydns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nuaavee.skybridge.events.listener.EventListener;
import com.nuaavee.skybridge.events.listener.EventListenerType;
import com.nuaavee.skybridge.events.type.StatusUpdateEvent;
import static com.nuaavee.skybridge.events.EventType.STATUS_UPDATE;

@Component
@EventListenerType(STATUS_UPDATE)
public class StatusUpdateEventListener implements EventListener<StatusUpdateEvent> {

  private static final Logger LOG = LoggerFactory.getLogger(StatusUpdateEventListener.class);

  @Autowired
  private DomainNameRegistrationService registrationService;

  @Override
  public void process(StatusUpdateEvent event) {
    switch (event.getTaskStatus()) {
      case TASK_RUNNING:
        LOG.info("task '{}' -- running on ports '{}' under '{}'", event.getTaskId(), event.getPorts(), event.getHost());
        registrationService.register(event.getTaskId(), event.getAppId(), event.getHost());
        break;
      case TASK_KILLED:
        LOG.info("task '{}' -- killed under '{}'", event.getTaskId(), event.getHost());
        registrationService.unregister(event.getTaskId(), event.getAppId(), event.getHost());
        break;
      case TASK_FAILED:
        LOG.info("task '{}' -- failed to run under '{}' due to '{}'",
                 event.getTaskId(), event.getHost(), event.getMessage());
        break;
      default:
        LOG.info("ignored status update event for task '{}' named '{}' since its status is '{}'",
                 event.getTaskId(), event.getAppId(), event.getTaskStatus());
    }
  }
}
