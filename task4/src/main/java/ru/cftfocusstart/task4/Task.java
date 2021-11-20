package ru.cftfocusstart.task4;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class Task implements Runnable {
    private final int startShiftIter;
    private final int totalThreadsNumber;
    private final int totalIter;
    private double resultValue;

    @Override
    public void run() {
        log.info("Calculations started");
        for (int i = startShiftIter; i < totalIter; i += totalThreadsNumber) {
            resultValue += 1 / Math.pow(2, i);
        }
        log.info("Calculations ended. Total value for the thread is: {}", resultValue);
    }

    public double getResultValue() {
        return resultValue;
    }
}
