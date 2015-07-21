package com.nuaavee.skybridge.events;

import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.google.common.collect.ImmutableMap;

public enum EventType {

  UNKNOWN(StringUtils.EMPTY),
  SUBSCRIBE("subscribe_event"),
  DEPLOYMENT_INFO("deployment_info"),
  DEPLOYMENT_SUCCESS("deployment_success"),
  STATUS_UPDATE("status_update_event");

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

  private EventType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static EventType forName(String name) {
    if (!LOOKUP.containsKey(name)) {
      return UNKNOWN;
    }
    return LOOKUP.get(name);
  }
}
