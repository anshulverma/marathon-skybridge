package com.nuaavee.skybridge.events;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.google.common.collect.Lists;
import com.nuaavee.skybridge.events.type.DeploymentInfoEvent;
import com.nuaavee.skybridge.events.type.DeploymentSuccessEvent;
import com.nuaavee.skybridge.events.type.MarathonEvent;
import com.nuaavee.skybridge.events.type.StatusUpdateEvent;
import com.nuaavee.skybridge.events.type.SubscribeEvent;
import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class EventMarshallerTest {

  @Parameters(name = "{index}: {0}")
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {
      {
        EventType.SUBSCRIBE,
        "features/events/subscribe.json",
        (MarathonEventBuilder) () -> {
          SubscribeEvent event = new SubscribeEvent();
          event.setEventType(EventType.SUBSCRIBE);
          event.setTimestamp(new DateTime("2014-03-01T23:29:30.158Z", DateTimeZone.UTC));
          event.setCallbackUrl("http://subscriber.acme.org/callbacks");
          event.setClientIp("1.2.3.4");
          return event;
        }
      },
      {
        EventType.STATUS_UPDATE,
        "features/events/status_update.json",
        (MarathonEventBuilder) () -> {
          StatusUpdateEvent event = new StatusUpdateEvent();
          event.setEventType(EventType.STATUS_UPDATE);
          event.setTimestamp(new DateTime("2014-03-01T23:29:30.158Z", DateTimeZone.UTC));
          event.setSlaveId("20140909-054127-177048842-5050-1494-0");
          event.setTaskId("my-app_0-1396592784349");
          event.setTaskStatus(TaskStatus.TASK_RUNNING);
          event.setAppId("/my-app");
          event.setHost("slave-1234.acme.org");
          event.setPorts(new int[] {31372});
          event.setVersion(new DateTime("2014-04-04T06:26:23.051Z", DateTimeZone.UTC));
          return event;
        }
      },
      {
        EventType.DEPLOYMENT_SUCCESS,
        "features/events/deployment_success.json",
        (MarathonEventBuilder) () -> {
          DeploymentSuccessEvent event = new DeploymentSuccessEvent();
          event.setEventType(EventType.DEPLOYMENT_SUCCESS);
          event.setTimestamp(new DateTime("2014-03-01T23:29:30.158Z", DateTimeZone.UTC));
          event.setId("867ed450-f6a8-4d33-9b0e-e11c5513990b");
          return event;
        }
      },
      {
        EventType.DEPLOYMENT_INFO,
        "features/events/deployment_info.json",
        (MarathonEventBuilder) () -> {
          DeploymentInfoEvent event = new DeploymentInfoEvent();
          event.setEventType(EventType.DEPLOYMENT_INFO);
          event.setTimestamp(new DateTime("2014-03-01T23:29:30.158Z", DateTimeZone.UTC));

          DeploymentInfoEvent.Step currentStep = new DeploymentInfoEvent.Step();
          currentStep.setAction("ScaleApplication");
          currentStep.setApp("/my-app");
          event.setCurrentStep(currentStep);

          DeploymentInfoEvent.Plan plan = new DeploymentInfoEvent.Plan();
          plan.setId("867ed450-f6a8-4d33-9b0e-e11c5513990b");
          plan.setVersion(new DateTime("2014-03-01T23:24:14.846Z", DateTimeZone.UTC));

          DeploymentInfoEvent.Group originalGroup = new DeploymentInfoEvent.Group();
          originalGroup.setApps(Collections.emptyList());
          originalGroup.setDependencies(Collections.emptyList());
          originalGroup.setGroups(Collections.emptyList());
          originalGroup.setId("/");
          originalGroup.setVersion(new DateTime("2014-09-09T06:30:49.667Z", DateTimeZone.UTC));
          plan.setOriginal(originalGroup);

          DeploymentInfoEvent.Group targetGroup = new DeploymentInfoEvent.Group();

          DeploymentInfoEvent.Service app = new DeploymentInfoEvent.Service();
          app.setId("/my-app");
          app.setPorts(new int[] {10001});
          app.setVersion(new DateTime("2014-09-09T05:57:50.866Z", DateTimeZone.UTC));
          targetGroup.setApps(Lists.newArrayList(app));

          targetGroup.setDependencies(Collections.emptyList());
          targetGroup.setGroups(Collections.emptyList());
          targetGroup.setId("/");
          targetGroup.setVersion(new DateTime("2014-09-09T05:57:50.866Z", DateTimeZone.UTC));
          plan.setTarget(targetGroup);

          DeploymentInfoEvent.Step step = new DeploymentInfoEvent.Step();
          step.setAction("ScaleApplication");
          step.setApp("/my-app");
          plan.setSteps(Lists.newArrayList(step));

          event.setPlan(plan);
          return event;
        }
      }
    });
  }

  @Parameter(0)
  public EventType eventType;

  @Parameter(1)
  public String eventJsonFile;

  @Parameter(2)
  public MarathonEventBuilder expectedMarathonEventBuilder;

  @Test
  public void test() throws IOException, UnknownEventTypeException {
    EventMarshaller marshaller = new EventMarshaller();
    Assert.assertEquals("unable to deserialize event type: " + eventType,
                        expectedMarathonEventBuilder.build(),
                        marshaller.unmarshall(readJson()));
  }

  private String readJson() throws IOException {
    ClassLoader classLoader = getClass().getClassLoader();
    return IOUtils.toString(classLoader.getResourceAsStream(eventJsonFile));
  }

  private static interface MarathonEventBuilder {
    MarathonEvent build();
  }
}
