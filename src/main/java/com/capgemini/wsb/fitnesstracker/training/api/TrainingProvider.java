package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.List;

/**
 * TrainingProvider is an interface for accessing and filtering Training records.
 */
public interface TrainingProvider {

    /**
     * Retrieves all trainings available in the system.
     *
     * @return A {@link List} of all available {@link Training} records.
     */
    List<Training> getAllTrainings();

    /**
     * Retrieves trainings associated with a specific user based on their ID.
     *
     * @param userId The ID of the user for whom trainings are being retrieved.
     * @return A {@link List} of {@link Training} records associated with the specified user.
     */
    List<Training> getUserTrainings(Long userId);

    /**
     * Retrieves all trainings that match a specified activity type.
     *
     * @param activityType The type of activity to filter trainings by.
     * @return A {@link List} of {@link Training} records with the specified activity type.
     */
    List<Training> getAllByActivityType(ActivityType activityType);

    /**
     * Retrieves all trainings that have ended after the specified date and time.
     *
     * @param afterTime The {@link Date} after which trainings must have ended to be included.
     * @return A {@link List} of {@link Training} records that ended after the specified time.
     */
    List<Training> getEndedTrainings(final Date afterTime);
}
