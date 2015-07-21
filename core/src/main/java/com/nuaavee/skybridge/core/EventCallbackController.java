package com.nuaavee.skybridge.core;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nuaavee.skybridge.core.error.JsonParsingException;
import com.nuaavee.skybridge.core.error.RestMvcException;
import com.nuaavee.skybridge.events.EventMarshaller;
import com.nuaavee.skybridge.events.EventProcessor;
import com.nuaavee.skybridge.events.UnknownEventTypeException;
import com.nuaavee.skybridge.events.type.MarathonEvent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
public class EventCallbackController {

  private static final Logger LOG = LoggerFactory.getLogger(EventCallbackController.class);

  @Autowired
  private EventProcessor eventProcessor;

  @Autowired
  private EventMarshaller eventMarshaller;

  @RequestMapping(value = "/event", method = RequestMethod.POST)
  @ResponseBody
  public EventHandlerResponse eventCallback(@RequestBody String eventJson)
    throws RestMvcException, UnknownEventTypeException {

    try {
      MarathonEvent event = eventMarshaller.unmarshall(eventJson);
      LOG.info("received event '{}'", event.getEventType());
      eventProcessor.process(event);
      LOG.info(event.toString());
      return EventHandlerResponse.OK;
    } catch (IOException e) {
      LOG.error("error while converting event json: " + eventJson);
      throw new JsonParsingException(e);
    }
  }

  @Data
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  private static class EventHandlerResponse {
    public static final EventHandlerResponse IGNORED = new EventHandlerResponse("ignored");
    public static final EventHandlerResponse OK = new EventHandlerResponse("ok");
    public static final EventHandlerResponse ERROR = new EventHandlerResponse("error");

    private String result;
  }
}
