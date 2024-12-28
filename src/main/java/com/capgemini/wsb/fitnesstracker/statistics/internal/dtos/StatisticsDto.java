package com.capgemini.wsb.fitnesstracker.statistics.internal.dtos;

import com.capgemini.wsb.fitnesstracker.user.api.dtos.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticsDto {
    private Long id;

    private UserDto user;

    private int totalTrainings;

    private double totalDistance;

    private int totalCaloriesBurned;
}
