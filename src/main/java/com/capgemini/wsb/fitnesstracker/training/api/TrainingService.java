package com.capgemini.wsb.fitnesstracker.training.api;

public interface TrainingService {
    /**
     * Creates a new training.
     *
     * @param training The training to be created.
     * @return The created {@link Training} instance.
     */
    Training createTraining(Training training);

    /**
     * Updates an existing training based on their ID.
     *
     * @param id   The ID of the training to be updated.
     * @param training The updated training information.
     */
    void updateTraining(Long id, Training training);
}
