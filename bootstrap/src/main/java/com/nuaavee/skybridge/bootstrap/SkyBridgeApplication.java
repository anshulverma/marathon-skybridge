package com.nuaavee.skybridge.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.nuaavee.skybridge.core.CoreConfiguration;

@SpringBootApplication
public class SkyBridgeApplication {

  public static void main(String[] args) {
    SpringApplication.run(CoreConfiguration.class, args);
  }

}
