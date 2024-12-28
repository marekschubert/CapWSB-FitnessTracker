package com.capgemini.wsb.fitnesstracker.statistics.api;

public class StatisticsNotFoundException extends RuntimeException {
    private StatisticsNotFoundException(String message) {
        super(message);
    }

    public StatisticsNotFoundException(Long id) {
        this("Statistics with ID=%s were not found".formatted(id));
    }
}
