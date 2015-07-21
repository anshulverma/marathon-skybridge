package com.nuaavee.skybridge.events.type;

import com.nuaavee.skybridge.events.TaskStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StatusUpdateEvent extends MarathonEvent {

  private TaskStatus taskStatus;

}
