package com.capgemini.wsb.fitnesstracker.statistics.internal.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateStatisticsDto {
    private int totalTrainings;

    private double totalDistance;

    private int totalCaloriesBurned;

}
