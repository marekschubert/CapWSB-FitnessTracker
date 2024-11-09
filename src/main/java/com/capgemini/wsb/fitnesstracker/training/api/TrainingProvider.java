package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.List;

public interface TrainingProvider {


    List<Training> getAllTrainings();

    List<Training> getUserTrainings(Long userId);

    List<Training> getAllByActivityType(ActivityType activityType);

    List<Training> getEndedTrainings(final Date afterTime);
}
