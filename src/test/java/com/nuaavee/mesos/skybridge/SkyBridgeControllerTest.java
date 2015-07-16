package com.nuaavee.mesos.skybridge;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SkyBridgeControllerTest extends AbstractSpringContextTest {

  @Autowired
  private Integer id;

  @Test
  public void testTest() throws Exception {
    mockMvc.perform(get("/test/" + id))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.message", is("I got id " + id + " and message: this is a test")));
  }
}
