package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsNotFoundException;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsProvider;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsService;
import com.capgemini.wsb.fitnesstracker.statistics.internal.dtos.CreateStatisticDto;
import com.capgemini.wsb.fitnesstracker.statistics.internal.dtos.UpdateStatisticsDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsServiceImpl implements StatisticsProvider, StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final UserProvider userProvider;
    private final StatisticsMapper statisticsMapper;

    @Override
    public List<Statistics> getUserStatistics(Long userId) {
        return statisticsRepository.findByUserId(userId);
    }

    @Override
    public List<Statistics> getStatisticsWithCaloriesGreaterThan(int calories) {
        return statisticsRepository.findCaloriesGreaterThan(calories);
    }

    @Override
    public void deleteStatistics(Long statisticsId) {
        statisticsRepository.deleteById(statisticsId);
    }

    @Override
    public Statistics createStatistics(CreateStatisticDto createStatisticDto) {
        Optional<User> user = userProvider.getUser(createStatisticDto.getUserId());
        if(user.isEmpty()){
            throw new UserNotFoundException(createStatisticDto.getUserId());
        }

        Statistics statistics = statisticsMapper.toEntity(createStatisticDto);
        statistics.setUser(user.get());

        return statisticsRepository.save(statistics);
    }

    @Override
    public void updateStatistics(Long statisticsId, UpdateStatisticsDto updateStatisticDto) {
        var foundStatistics = statisticsRepository.findById(statisticsId);
        if(foundStatistics.isEmpty()){
            throw new StatisticsNotFoundException(statisticsId);
        }

        Statistics statistics = statisticsMapper.toEntity(updateStatisticDto);

        statistics.setId(statisticsId);
        statistics.setUser(foundStatistics.get().getUser());

        statisticsRepository.save(statistics);
    }
}
