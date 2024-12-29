package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.internal.dtos.CreateStatisticDto;
import com.capgemini.wsb.fitnesstracker.statistics.internal.dtos.StatisticsDto;
import com.capgemini.wsb.fitnesstracker.statistics.internal.dtos.UpdateStatisticsDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class StatisticsMapper {

    public StatisticsMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    private final UserMapper userMapper;


    public StatisticsDto toDto(Statistics statistics) {
        var userDto = userMapper.toDto(statistics.getUser());

        var statisticsDto = new StatisticsDto();

        statisticsDto.setId(statistics.getId());
        statisticsDto.setUser(userDto);
        statisticsDto.setTotalDistance(statistics.getTotalDistance());
        statisticsDto.setTotalTrainings(statistics.getTotalTrainings());
        statisticsDto.setTotalCaloriesBurned(statistics.getTotalCaloriesBurned());

        return statisticsDto;
    }

    public Statistics toEntity(CreateStatisticDto createStatisticDto) {
        var entity = new Statistics();

        entity.setTotalDistance(createStatisticDto.getTotalDistance());
        entity.setTotalTrainings(createStatisticDto.getTotalTrainings());
        entity.setTotalCaloriesBurned(createStatisticDto.getTotalCaloriesBurned());

        return entity;
    }

    public Statistics toEntity(UpdateStatisticsDto updateStatisticsDto) {
        var entity = new Statistics();

        entity.setTotalDistance(updateStatisticsDto.getTotalDistance());
        entity.setTotalTrainings(updateStatisticsDto.getTotalTrainings());
        entity.setTotalCaloriesBurned(updateStatisticsDto.getTotalCaloriesBurned());

        return entity;
    }
}
