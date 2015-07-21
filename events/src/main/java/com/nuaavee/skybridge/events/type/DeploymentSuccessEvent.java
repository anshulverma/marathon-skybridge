package com.nuaavee.skybridge.events.type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeploymentSuccessEvent extends MarathonEvent {

  private String id;

}
