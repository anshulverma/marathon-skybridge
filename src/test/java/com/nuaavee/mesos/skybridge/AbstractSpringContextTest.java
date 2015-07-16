package com.nuaavee.mesos.skybridge;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration(classes = {TestSkyBridgeConfiguration.class, SkyBridgeConfiguration.class})
@Configuration
@WebAppConfiguration
public class AbstractSpringContextTest extends AbstractJUnit4SpringContextTests {

  @Autowired
  protected WebApplicationContext applicationContext;

  protected MockMvc mockMvc;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
  }
}
