package com.nuaavee.skybridge.events;

public class UnknownEventTypeException extends Exception {

  private String eventType;

  public UnknownEventTypeException(String eventType) {
    super("unknown event type: " + eventType);
    this.eventType = eventType;
  }

  public String getEventType() {
    return eventType;
  }
}
