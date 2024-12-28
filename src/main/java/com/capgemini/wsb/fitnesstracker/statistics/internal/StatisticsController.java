package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsProvider;
import com.capgemini.wsb.fitnesstracker.statistics.internal.dtos.StatisticsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsProvider statisticsProvider;

    private final StatisticsMapper statisticsMapper;

    @GetMapping("/user/{userId}")
    public List<StatisticsDto> getUserStatistic(@PathVariable("userId") Long userId) {
        return statisticsProvider
                .getUserStatistics(userId)
                .stream()
                .map(statisticsMapper::toDto)
                .toList();
    }
}
