package com.nuaavee.skybridge.events.type;

import org.joda.time.DateTime;
import com.nuaavee.skybridge.events.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public abstract class MarathonEvent {

  private EventType eventType;

  private DateTime timestamp;

}
