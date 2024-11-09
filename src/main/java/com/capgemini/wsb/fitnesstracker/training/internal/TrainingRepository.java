package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Date;

interface TrainingRepository extends JpaRepository<Training, Long> {
    default List<Training> findUserTrainings(Long userId) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getId(), userId))
                .toList();
    }

    default List<Training> findEndedTrainings(Date afterTime) {
        return findAll().stream()
                .filter(training -> training.getEndTime().before(afterTime))
                .toList();
    }

    List<Training> findTrainingByActivityType(ActivityType activityType);

}
