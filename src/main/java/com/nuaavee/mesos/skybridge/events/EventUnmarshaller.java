package com.nuaavee.mesos.skybridge.events;

import java.io.IOException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class EventUnmarshaller {

  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final String EVENT_TYPE_KEY = "eventType";

  static {
    MAPPER.registerModule(new JodaModule());
  }

  private EventUnmarshaller() { }

  public static MarathonEvent unmarshall(String json) throws IOException {
    TreeNode tree = MAPPER.readTree(json);
    EventType eventType = getEventType(tree);
    return MAPPER.readValue(tree.traverse(), eventType.getEventClass());
  }

  private static EventType getEventType(TreeNode tree) {
    TreeNode eventTypeNode = tree.get(EVENT_TYPE_KEY);
    if (!(eventTypeNode instanceof TextNode)) {
      return EventType.UNKNOWN;
    }

    return EventType.forName(((TextNode) eventTypeNode).textValue());
  }
}
