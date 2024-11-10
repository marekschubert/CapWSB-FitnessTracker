package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.dtos.CreateTrainingDto;
import com.capgemini.wsb.fitnesstracker.training.internal.dtos.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.internal.dtos.UpdateTrainingDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import org.hibernate.sql.Update;
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

    Training toEntity(CreateTrainingDto createTrainingDto){
        return new Training(null,
                createTrainingDto.getStartTime(),
                createTrainingDto.getEndTime(),
                createTrainingDto.getActivityType(),
                createTrainingDto.getDistance(),
                createTrainingDto.getAverageSpeed()
        );
    }

    Training toEntity(UpdateTrainingDto updateTrainingDto){
        return new Training(null,
                updateTrainingDto.getStartTime(),
                updateTrainingDto.getEndTime(),
                updateTrainingDto.getActivityType(),
                updateTrainingDto.getDistance(),
                updateTrainingDto.getAverageSpeed()
        );
    }

}
