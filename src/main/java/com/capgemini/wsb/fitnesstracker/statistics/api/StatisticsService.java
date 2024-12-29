package com.capgemini.wsb.fitnesstracker.statistics.api;

import com.capgemini.wsb.fitnesstracker.statistics.internal.dtos.CreateStatisticDto;
import com.capgemini.wsb.fitnesstracker.statistics.internal.dtos.UpdateStatisticsDto;

public interface StatisticsService {
    void deleteStatistics(Long statisticsId);
    Statistics createStatistics(CreateStatisticDto createStatisticDto);
    void updateStatistics(Long statisticsId, UpdateStatisticsDto updateStatisticDto);
}
