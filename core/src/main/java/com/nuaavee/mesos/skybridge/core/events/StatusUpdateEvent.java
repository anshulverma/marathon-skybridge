package com.nuaavee.mesos.skybridge.core.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StatusUpdateEvent extends MarathonEvent {

  private TaskStatus taskStatus;

}
