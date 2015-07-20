package com.nuaavee.mesos.skybridge.events;

public class UnknownEventTypeException extends Exception {

  public UnknownEventTypeException(String eventTypeName) {
    super("unknown event type: " + eventTypeName);
  }
}
