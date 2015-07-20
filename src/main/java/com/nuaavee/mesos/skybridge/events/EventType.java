package com.nuaavee.mesos.skybridge.events;

import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.google.common.collect.ImmutableMap;

public enum EventType {

  UNKNOWN(StringUtils.EMPTY, null),
  SUBSCRIBE("subscribe_event", SubscribeEvent.class),
  DEPLOYMENT_INFO("deployment_info", DeploymentInfoEvent.class),
  DEPLOYMENT_SUCCESS("deployment_success", DeploymentSuccessEvent.class),
  STATUS_UPDATE("status_update_event", StatusUpdateEvent.class);

  private static final Map<String, EventType> LOOKUP;

  static {
    ImmutableMap.Builder<String, EventType> builder = new ImmutableMap.Builder<>();
    for (EventType eventType : EventType.values()) {
      if (eventType == UNKNOWN) {
        continue;
      }
      builder.put(eventType.getName(), eventType);
    }
    LOOKUP = builder.build();
  }

  private final String name;
  private final Class<? extends MarathonEvent> eventClass;

  private EventType(String name, Class<? extends MarathonEvent> eventClass) {
    this.name = name;
    this.eventClass = eventClass;
  }

  public String getName() {
    return name;
  }

  public Class<? extends MarathonEvent> getEventClass() {
    return eventClass;
  }

  public static EventType forName(String name) {
    if (!LOOKUP.containsKey(name)) {
      return UNKNOWN;
    }
    return LOOKUP.get(name);
  }
}
