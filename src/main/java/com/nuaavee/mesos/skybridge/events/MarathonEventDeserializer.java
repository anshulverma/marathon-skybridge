package com.nuaavee.mesos.skybridge.events;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MarathonEventDeserializer extends JsonDeserializer {

  @Override
  public Object deserialize(JsonParser jsonParser, DeserializationContext context)
    throws IOException, JsonProcessingException {
    TreeNode tree = jsonParser.readValueAsTree();
    ObjectMapper mapper = new ObjectMapper();
    Object event = jsonParser.readValueAs(MarathonEvent.class);
    return new ObjectMapper().treeToValue(tree, SubscribeEvent.class);
  }
}
