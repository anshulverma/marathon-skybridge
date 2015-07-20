package com.nuaavee.mesos.skybridge.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.nuaavee.mesos.skybridge.core.SkyBridgeContext;

@SpringBootApplication
public class SkyBridgeApplication {

  public static void main(String[] args) {
    SpringApplication.run(SkyBridgeContext.class, args);
  }

}
