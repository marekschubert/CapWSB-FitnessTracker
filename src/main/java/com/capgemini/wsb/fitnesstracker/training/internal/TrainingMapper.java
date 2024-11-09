package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.dtos.TrainingDto;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    public TrainingMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }
    private final UserMapper userMapper;

    TrainingDto toDto(Training training){
        var userDto = userMapper.toDto(training.getUser());

        return new TrainingDto(userDto,
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    Training toEntity(TrainingDto trainingDto){
        var user = userMapper.toEntity(trainingDto.getUser());

        return new Training(user,
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed()
        );
    }

}
