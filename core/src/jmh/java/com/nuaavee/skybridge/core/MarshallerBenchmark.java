package com.nuaavee.skybridge.core;

import java.io.IOException;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import com.nuaavee.skybridge.events.EventMarshaller;
import com.nuaavee.skybridge.events.UnknownEventTypeException;

@State(Scope.Group)
public class MarshallerBenchmark {

  private EventMarshaller marshaller;

  @Setup
  public void setup() {
    marshaller = new EventMarshaller();
  }

  @Benchmark @Group("serialize") @GroupThreads(4)
  public void empty_status_update() throws IOException, UnknownEventTypeException {
    marshaller.unmarshall("{" +
                            "\"eventType\": \"status_update_event\"," +
                            "\"timestamp\": \"2014-03-01T23:29:30.158Z\"," +
                            "\"slaveId\": \"20140909-054127-177048842-5050-1494-0\"," +
                            "\"taskId\": \"my-app_0-1396592784349\"," +
                            "\"taskStatus\": \"TASK_RUNNING\"," +
                            "\"appId\": \"/my-app\"," +
                            "\"host\": \"slave-1234.acme.org\"," +
                            "\"ports\": [31372]," +
                            "\"version\": \"2014-04-04T06:26:23.051Z\"," +
                            "\"message\": \"something happened\"" +
                          "}");
  }
}
