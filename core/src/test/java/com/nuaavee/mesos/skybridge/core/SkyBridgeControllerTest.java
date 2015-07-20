package com.nuaavee.mesos.skybridge.core;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SkyBridgeControllerTest extends AbstractSpringContextTest {

  @Autowired
  private Integer id;

  @Test
  public void testTest() throws Exception {
//    mockMvc.perform(get("/test/" + id))
//           .andExpect(status().isOk())
//           .andExpect(jsonPath("$.message", is("I got id " + id + " and message: this is a test")));
  }
}
