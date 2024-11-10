package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.training.internal.dtos.CreateTrainingDto;
import com.capgemini.wsb.fitnesstracker.training.internal.dtos.UpdateTrainingDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingService, TrainingProvider {
    private final TrainingRepository trainingRepository;
    private final UserProvider userProvider;
    private final TrainingMapper trainingMapper;


    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getUserTrainings(final Long userId) {
        return trainingRepository.findByUserId(userId);
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
    public Training createTraining(final CreateTrainingDto createTrainingDto) {
        Optional<User> user = userProvider.getUser(createTrainingDto.getUserId());
        if(user.isEmpty()){
            throw new UserNotFoundException(createTrainingDto.getUserId());
        }

        Training training = trainingMapper.toEntity(createTrainingDto);
        training.setUser(user.get());

        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(Long id, UpdateTrainingDto updateTrainingDto) {
        Optional<User> user = userProvider.getUser(updateTrainingDto.getUserId());
        if(user.isEmpty()){
            throw new UserNotFoundException(updateTrainingDto.getUserId());
        }

        Training training = trainingMapper.toEntity(updateTrainingDto);
        training.setUser(user.get());
        training.setId(id);

        return trainingRepository.save(training);
    }
}
