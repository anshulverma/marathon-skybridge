package com.nuaavee.mesos.skybridge.events;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SubscribeEvent extends MarathonEvent {

  private String clientIp;
  private String callbackUrl;

}
