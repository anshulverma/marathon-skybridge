package com.nuaavee.mesos.skybridge.core.events;

public class UnknownEventTypeException extends Exception {

  public UnknownEventTypeException(String eventTypeName) {
    super("unknown event type: " + eventTypeName);
  }
}
