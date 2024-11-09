package com.capgemini.wsb.fitnesstracker.training.internal;


import com.capgemini.wsb.fitnesstracker.training.internal.dtos.TrainingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;

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
    public List<TrainingDto> getEndedTrainings(@PathVariable("id") Date afterTime) {
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
    public TrainingDto addTraining(@RequestBody TrainingDto trainingDto) {

        return userMapper.toDto(service.createTraining(userMapper.toEntity(trainingDto)));
    }

    @PutMapping("/{id}")
    public void updateTraining(@PathVariable("id") Long id, @RequestBody TrainingDto trainingDto){
        service.updateTraining(id, userMapper.toEntity(trainingDto));
    }

}
