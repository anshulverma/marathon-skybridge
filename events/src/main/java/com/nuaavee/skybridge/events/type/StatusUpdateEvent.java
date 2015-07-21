package com.nuaavee.skybridge.events.type;

import org.joda.time.DateTime;
import com.nuaavee.skybridge.events.TaskStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StatusUpdateEvent extends MarathonEvent {

    private String slaveId;

    private String taskId;

    private TaskStatus taskStatus;

    private String appId;

    private String host;

    private int[] ports;

    private DateTime version;

}
