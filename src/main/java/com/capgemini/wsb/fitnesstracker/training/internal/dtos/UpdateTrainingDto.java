package com.capgemini.wsb.fitnesstracker.training.internal.dtos;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import lombok.Getter;

import java.util.Date;

@Getter
public class UpdateTrainingDto {
    private final Long userId;
    private final Date startTime;
    private final Date endTime;
    private final ActivityType activityType;
    private final double distance;
    private final double averageSpeed;


    public UpdateTrainingDto(
            final Long userId,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}
