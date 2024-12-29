package com.capgemini.wsb.fitnesstracker.statistics.api;

import java.util.List;

public interface StatisticsProvider {

    List<Statistics> getUserStatistics(Long userId);
    List<Statistics> getStatisticsWithCaloriesGreaterThan(int calories);
}
