package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Date;

interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Finds trainings associated with a specific user ID.
     *
     * @param userId The ID of the user whose trainings are to be retrieved.
     * @return A {@link List} of {@link Training} records associated with the specified user ID.
     */
    List<Training> findByUserId(Long userId);

    /**
     * Finds all trainings that have ended after the specified date and time.
     *
     * @param afterTime The {@link Date} after which trainings must have ended to be included.
     * @return A {@link List} of {@link Training} records that ended after the specified date and time.
     */
    default List<Training> findEndedTrainings(Date afterTime) {
        return findAll().stream()
                .filter(training -> training.getEndTime().after(afterTime))
                .toList();
    }

    /**
     * Finds all trainings with the specified activity type.
     *
     * @param activityType The type of activity to filter trainings by.
     * @return A {@link List} of {@link Training} records that match the specified activity type.
     */
    List<Training> findTrainingByActivityType(ActivityType activityType);

}
