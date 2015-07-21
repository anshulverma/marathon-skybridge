package com.nuaavee.skybridge.events.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nuaavee.skybridge.events.EventType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter(AccessLevel.NONE)
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class MarathonEvent {

  private EventType eventType;

}
