package com.nuaavee.mesos.skybridge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestSkyBridgeConfiguration {

  @Bean
  Integer createSampleID() {
    return 123;
  }
}
