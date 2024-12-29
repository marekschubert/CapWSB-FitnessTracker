package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsProvider;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsService;
import com.capgemini.wsb.fitnesstracker.statistics.internal.dtos.CreateStatisticDto;
import com.capgemini.wsb.fitnesstracker.statistics.internal.dtos.StatisticsDto;
import com.capgemini.wsb.fitnesstracker.statistics.internal.dtos.UpdateStatisticsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsProvider statisticsProvider;

    private final StatisticsMapper statisticsMapper;
    private final StatisticsService statisticsService;

    @GetMapping("/user/{userId}")
    public List<StatisticsDto> getUserStatistic(@PathVariable("userId") Long userId) {
        return statisticsProvider
                .getUserStatistics(userId)
                .stream()
                .map(statisticsMapper::toDto)
                .toList();
    }

    @GetMapping("/calories")
    public List<StatisticsDto> getWithCaloriesGreaterThan(@RequestParam("calories") int calories) {
        return statisticsProvider
                .getStatisticsWithCaloriesGreaterThan(calories)
                .stream()
                .map(statisticsMapper::toDto)
                .toList();
    }

    @DeleteMapping("/{statisticsId}")
    public void deleteStatistic(@PathVariable("statisticsId") Long statisticsId) {
        statisticsService.deleteStatistics(statisticsId);
    }

    @PostMapping()
    public StatisticsDto createStatistic(@RequestBody CreateStatisticDto createStatisticDto) {
        return statisticsMapper.toDto(statisticsService.createStatistics(createStatisticDto));
    }

    @PutMapping("/{id}")
    public void updateStatistics(@PathVariable("id") Long id, @RequestBody UpdateStatisticsDto updateStatisticsDto){
        statisticsService.updateStatistics(id, updateStatisticsDto);
    }
}
