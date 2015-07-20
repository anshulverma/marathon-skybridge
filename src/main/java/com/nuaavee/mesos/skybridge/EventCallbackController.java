package com.nuaavee.mesos.skybridge;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nuaavee.mesos.skybridge.events.EventUnmarshaller;
import com.nuaavee.mesos.skybridge.events.MarathonEvent;
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
      MarathonEvent event= EventUnmarshaller.unmarshall(eventJson);
      System.out.println(event.toString());
    } catch (IOException e) {
      LOG.error("error while converting event json: " + eventJson, e);
    }
//    if (event.getEventType() == null) {
//      LOG.warn("recieved unknown event (ignored)");
//      return EventHandlerResponse.IGNORED;
//    }
//    LOG.info("recieved event '{}'", event.getEventType());
    return EventHandlerResponse.OK;
  }

  @Data
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  private static class EventHandlerResponse {
    public static final EventHandlerResponse IGNORED = new EventHandlerResponse("ignored");
    public static final EventHandlerResponse OK = new EventHandlerResponse("ok");

    private String result;
  }
}
