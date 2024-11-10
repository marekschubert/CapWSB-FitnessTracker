package com.capgemini.wsb.fitnesstracker.training.internal;


import com.capgemini.wsb.fitnesstracker.training.internal.dtos.CreateTrainingDto;
import com.capgemini.wsb.fitnesstracker.training.internal.dtos.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.internal.dtos.UpdateTrainingDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl service;

    private final TrainingMapper userMapper;


    @GetMapping("/{id}")
    public List<TrainingDto> getUserTrainings(@PathVariable("id") Long userId) {
        return service.getUserTrainings(userId)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return service.getAllTrainings()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getEndedTrainings(@PathVariable("afterTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime) {
        return service.getEndedTrainings(afterTime)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivityType(@RequestParam("activityType") ActivityType activityType) {
        return service.getAllByActivityType(activityType)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TrainingDto addTraining(@RequestBody CreateTrainingDto createTrainingDto) {
        return userMapper.toDto(service.createTraining(createTrainingDto));
    }

    @PutMapping("/{id}")
    public TrainingDto updateTraining(@PathVariable("id") Long id, @RequestBody UpdateTrainingDto updateTrainingDto){
        return userMapper.toDto(service.updateTraining(id, updateTrainingDto));
    }

}
