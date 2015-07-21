package com.nuaavee.skybridge.core.error;

import java.io.IOException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.nuaavee.skybridge.core.AbstractSpringContextTest;
import com.nuaavee.skybridge.events.EventMarshaller;
import com.nuaavee.skybridge.events.UnknownEventTypeException;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ErrorHandlerTest extends AbstractSpringContextTest {

  @Autowired
  private EventMarshaller marshaller;

  @Test
  public void testEventParseError() throws Exception {
    String json = "{}";
    doThrow(new IOException("io error")).when(marshaller).unmarshall(json);
    mockMvc.perform(post("/event").content(json))
           .andExpect(status().isBadRequest())
           .andExpect(jsonPath("$.message", is("unable to parse event json, cause: java.io.IOException: io error")));
  }

  @Test
  public void testEventTypeError() throws Exception {
    String json = "{}";
    doThrow(new UnknownEventTypeException("xyz")).when(marshaller).unmarshall(json);
    mockMvc.perform(post("/event").content(json))
           .andExpect(status().isBadRequest())
           .andExpect(jsonPath("$.message", is("unknown event type: xyz")));
  }

  @Test
  public void testGenericError() throws Exception {
    String json = "{}";
    doThrow(new RuntimeException("xyz")).when(marshaller).unmarshall(json);
    mockMvc.perform(post("/event").content(json))
           .andExpect(status().isInternalServerError())
           .andExpect(jsonPath("$.message", is("generic error: xyz")));
  }
}
