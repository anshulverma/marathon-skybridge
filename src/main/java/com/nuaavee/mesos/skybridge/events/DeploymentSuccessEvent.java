package com.nuaavee.mesos.skybridge.events;

import org.joda.time.DateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeploymentSuccessEvent extends MarathonEvent {

  private DateTime timestamp;

}
