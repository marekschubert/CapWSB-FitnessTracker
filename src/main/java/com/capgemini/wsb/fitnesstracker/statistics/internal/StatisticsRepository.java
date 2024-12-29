package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    List<Statistics> findByUserId(Long userId);


    default List<Statistics> findCaloriesGreaterThan(int calories) {
        return findAll().stream()
                .filter(statistics -> statistics.getTotalCaloriesBurned() > calories)
                .toList();
    }
}
