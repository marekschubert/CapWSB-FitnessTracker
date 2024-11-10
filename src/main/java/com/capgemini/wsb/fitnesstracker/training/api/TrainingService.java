package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.dtos.CreateTrainingDto;
import com.capgemini.wsb.fitnesstracker.training.internal.dtos.UpdateTrainingDto;

public interface TrainingService {
    /**
     * Creates a new training.
     *
     * @param createTrainingDto The training to be created.
     * @return The created {@link Training} instance.
     */
    Training createTraining(CreateTrainingDto createTrainingDto);

    /**
     * Updates an existing training based on their ID.
     *
     * @param id   The ID of the training to be updated.
     * @param updateTrainingDto The updated training information.
     */
    Training updateTraining(Long id, UpdateTrainingDto updateTrainingDto);
}
