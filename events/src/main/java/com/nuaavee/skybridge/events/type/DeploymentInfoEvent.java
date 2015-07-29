package com.nuaavee.skybridge.events.type;

import java.util.List;
import org.joda.time.DateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeploymentInfoEvent extends MarathonEvent {

  private Plan plan;

  private Step currentStep;

  @Data
  @EqualsAndHashCode
  @ToString
  public static class Plan {

    private String id;

    private DateTime version;

    private Group original;

    private Group target;

    private List<Step> steps;

  }

  @Data
  @EqualsAndHashCode
  @ToString
  public static class Group {

    private String id;

    private DateTime version;

    private List<String> dependencies;

    private List<Group> groups;

    private List<Service> apps;

  }

  @Data
  @EqualsAndHashCode
  @ToString
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Service {

    private String id;

    private DateTime version;

    private int[] ports;

  }

  @Data
  @EqualsAndHashCode
  @ToString
  public static class Step {

    private List<Action> actions;

  }

  @Data
  @EqualsAndHashCode
  @ToString
  public static class Action {

    private String type;

    private String app;

  }
}
