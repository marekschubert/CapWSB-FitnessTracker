package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingService, TrainingProvider {
    private final TrainingRepository trainingRepository;

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getUserTrainings(final Long userId) {
        return trainingRepository.findUserTrainings(userId);
    }

    @Override
    public List<Training> getAllByActivityType(ActivityType activityType) {
        return  trainingRepository.findTrainingByActivityType(activityType);
    }

    @Override
    public List<Training> getEndedTrainings(final Date afterTime) {
        return trainingRepository.findEndedTrainings(afterTime);
    }

    @Override
    public Training createTraining(final Training training) {
        log.info("Creating Training {}", training);
        if (training.getId() != null) {
            throw new IllegalArgumentException("Training has already DB ID, update is not permitted!");
        }
        return trainingRepository.save(training);
    }

    @Override
    public void updateTraining(Long id, Training training) {
        var foundTraining = trainingRepository.findById(id);
        if(foundTraining.isEmpty()){
            throw new UserNotFoundException(id);
        }

        training.setId(id);
        trainingRepository.save(training);
    }
}
