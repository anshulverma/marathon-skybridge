package com.nuaavee.skybridge.core;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nuaavee.skybridge.events.MarathonEventSubscriber;

@Component
public class EventEndpointRegistrar {
  private static final Logger LOG = LoggerFactory.getLogger(CoreConfiguration.class);

  @Autowired
  private ApplicationConfig config;

  @Autowired
  private MarathonEventSubscriber eventSubscriber;

  @PostConstruct
  public void registerEventCallback() {
    eventSubscriber.subscribe(getMarathonUrl(), getCallbackUrl());
    LOG.info("registered event callback at '{}'", config.getMarathonUrl());
  }

  @PreDestroy
  public void unregisterEventCallback() {
    eventSubscriber.unsubscribe(getMarathonUrl(), getCallbackUrl());
    LOG.info("unregistered event callback at '{}'", config.getMarathonUrl());
  }

  private String getMarathonUrl() {
    if (StringUtils.isBlank(config.getMarathonUrl())) {
      throw new RuntimeException("missing parameter '--marathon.url'");
    }
    return config.getMarathonUrl();
  }

  private String getCallbackUrl() {
    if (StringUtils.isNotBlank(config.getCallbackUrl())) {
      return config.getCallbackUrl();
    }
    try {
      return "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + config.getServerPort() + "/event";
    } catch (UnknownHostException e) {
      throw new RuntimeException("unable to build callback url", e);
    }
  }
}
