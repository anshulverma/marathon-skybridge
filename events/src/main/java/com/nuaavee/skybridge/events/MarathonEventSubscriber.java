package com.nuaavee.skybridge.events;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.google.common.collect.ImmutableMap;
import com.nuaavee.skybridge.events.type.SubscribeEvent;

@Component
public class MarathonEventSubscriber {

  private static final String EVENT_SUBSCRIPTION_URL_TEMPLATE =
    "{marathonURL}/v2/eventSubscriptions?callbackUrl={callbackURL}";

  public void unsubscribe(String marathonURL, String callbackURL) {
    sendRequest(HttpMethod.DELETE, marathonURL, callbackURL);
  }

  public void subscribe(String marathonURL, String callbackURL) {
    sendRequest(HttpMethod.DELETE, marathonURL, callbackURL);
  }

  private void sendRequest(HttpMethod httpMethod, String marathonURL, String callbackURL) {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    restTemplate.exchange(EVENT_SUBSCRIPTION_URL_TEMPLATE,
                          httpMethod,
                          new HttpEntity<>(null, headers),
                          SubscribeEvent.class,
                          ImmutableMap.of("marathonURL", marathonURL,
                                          "callbackURL", callbackURL));
  }
}
