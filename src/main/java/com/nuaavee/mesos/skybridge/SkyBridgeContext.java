package com.nuaavee.mesos.skybridge;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import com.google.common.collect.ImmutableMap;
import com.nuaavee.mesos.skybridge.events.SubscribeEvent;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SkyBridgeContext {

  private static final Logger LOG = LoggerFactory.getLogger(SkyBridgeContext.class);
  private static final String EVENT_SUBSCRIPTION_URL_TEMPLATE =
    "{marathonURL}/v2/eventSubscriptions?callbackUrl={callbackURL}";

  @Autowired
  private ApplicationConfig config;

  @PostConstruct
  public void registerEventCallback() {
    sendRequest(HttpMethod.POST);
    LOG.info("registered event callback at '{}'", config.getMarathonUrl());
  }

  @PreDestroy
  public void unregisterEventCallback() {
    sendRequest(HttpMethod.DELETE);
    LOG.info("unregistered event callback at '{}'", config.getMarathonUrl());
  }

  private void sendRequest(HttpMethod httpMethod) {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    restTemplate.exchange(EVENT_SUBSCRIPTION_URL_TEMPLATE,
                          httpMethod,
                          new HttpEntity<>(null, headers),
                          SubscribeEvent.class,
                          ImmutableMap.of("marathonURL", getMarathonUrl(),
                                          "callbackURL", getCallbackUrl()));
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
