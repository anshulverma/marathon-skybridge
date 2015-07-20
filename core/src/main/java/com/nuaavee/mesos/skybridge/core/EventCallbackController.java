package com.nuaavee.mesos.skybridge.core;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nuaavee.mesos.skybridge.core.events.EventUnmarshaller;
import com.nuaavee.mesos.skybridge.core.events.MarathonEvent;
import com.nuaavee.mesos.skybridge.core.events.UnknownEventTypeException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
public class EventCallbackController {

  private static final Logger LOG = LoggerFactory.getLogger(EventCallbackController.class);

  @RequestMapping(value = "/event", method = RequestMethod.POST)
  @ResponseBody
  public EventHandlerResponse eventCallback(@RequestBody String eventJson) {
    try {
      MarathonEvent event = EventUnmarshaller.unmarshall(eventJson);
      LOG.info("recieved event '{}'", event.getEventType());
      LOG.info(event.toString());
      return EventHandlerResponse.OK;
    } catch (IOException e) {
      LOG.error("error while converting event json: " + eventJson, e);
      return EventHandlerResponse.ERROR;
    } catch (UnknownEventTypeException e) {
      LOG.warn(e.getMessage());
      return EventHandlerResponse.IGNORED;
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
