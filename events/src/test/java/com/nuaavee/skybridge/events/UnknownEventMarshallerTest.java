package com.nuaavee.skybridge.events;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class UnknownEventMarshallerTest {

  @Test
  public void testEmptyJson() throws IOException{
    try {
      new EventMarshaller().unmarshall("{}");
      Assert.fail("expected " + UnknownEventTypeException.class + " to be thrown");
    } catch (UnknownEventTypeException e) {
      Assert.assertEquals("event type in error should be null", null, e.getEventType());
    }
  }

  @Test
  public void testInvalidEventJson() throws IOException {
    try {
      new EventMarshaller().unmarshall("{\"eventType\":\"this_is_unknown\"}");
      Assert.fail("expected " + UnknownEventTypeException.class + " to be thrown");
    } catch (UnknownEventTypeException e) {
      Assert.assertEquals("event type in error should be 'this_is_unknown'", "this_is_unknown", e.getEventType());
    }
  }

}
