package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Date;

interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findByUserId(Long userId);

    default List<Training> findEndedTrainings(Date afterTime) {
        return findAll().stream()
                .filter(training -> training.getEndTime().after(afterTime))
                .toList();
    }

    List<Training> findTrainingByActivityType(ActivityType activityType);

}
