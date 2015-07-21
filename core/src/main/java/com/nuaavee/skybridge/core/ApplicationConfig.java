package com.nuaavee.skybridge.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Configuration
@Data
@Setter(AccessLevel.NONE)
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

  @Value("${marathon.url}")
  private String marathonUrl;

  // override this when running inside a docker container
  @Value("${callback.url}")
  private String callbackUrl;

  @Value("${server.port}")
  private String serverPort;
}
