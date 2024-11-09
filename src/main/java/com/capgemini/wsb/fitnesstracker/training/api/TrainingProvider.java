package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Optional;

public interface TrainingProvider {


    List<Training> getAllTrainings();

}
