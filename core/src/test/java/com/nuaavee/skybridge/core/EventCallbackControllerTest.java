package com.nuaavee.skybridge.core;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.nuaavee.skybridge.events.EventMarshaller;
import com.nuaavee.skybridge.events.type.MarathonEvent;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EventCallbackControllerTest extends AbstractSpringContextTest {

  @Autowired
  private EventMarshaller marshaller;

  @Test
  public void testEventParseError() throws Exception {
    String json = "{}";
    doReturn(new MarathonEvent() {}).when(marshaller).unmarshall(json);
    mockMvc.perform(post("/event").content(json))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.message", is("success")));
  }
}
