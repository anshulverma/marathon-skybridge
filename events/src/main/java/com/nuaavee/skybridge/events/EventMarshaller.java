package com.nuaavee.skybridge.events;

import java.io.IOException;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.collect.ImmutableMap;
import com.nuaavee.skybridge.events.type.DeploymentInfoEvent;
import com.nuaavee.skybridge.events.type.DeploymentSuccessEvent;
import com.nuaavee.skybridge.events.type.MarathonEvent;
import com.nuaavee.skybridge.events.type.StatusUpdateEvent;
import com.nuaavee.skybridge.events.type.SubscribeEvent;

@Component
public class EventMarshaller {

  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final String EVENT_TYPE_KEY = "eventType";
  private static final Map<EventType, Class<? extends MarathonEvent>> EVENT_CLASS_MAP =
    ImmutableMap.<EventType, Class<? extends MarathonEvent>>builder()
                .put(EventType.SUBSCRIBE, SubscribeEvent.class)
                .put(EventType.DEPLOYMENT_INFO, DeploymentInfoEvent.class)
                .put(EventType.DEPLOYMENT_SUCCESS, DeploymentSuccessEvent.class)
                .put(EventType.STATUS_UPDATE, StatusUpdateEvent.class)
                .build();

  static {
    MAPPER.registerModule(new JodaModule());
  }

  public MarathonEvent unmarshall(String json) throws IOException, UnknownEventTypeException {
    TreeNode tree = MAPPER.readTree(json);
    String eventTypeName = getEventTypeName(tree);
    EventType eventType = EventType.forName(eventTypeName);
    if (eventType == EventType.UNKNOWN) {
      throw new UnknownEventTypeException(eventTypeName);
    }
    return MAPPER.readValue(tree.traverse(), EVENT_CLASS_MAP.get(eventType));
  }

  private String getEventTypeName(TreeNode tree) {
    TreeNode eventTypeNode = tree.get(EVENT_TYPE_KEY);
    if (!(eventTypeNode instanceof TextNode)) {
      return null;
    }
    return ((TextNode) eventTypeNode).textValue();
  }
}
