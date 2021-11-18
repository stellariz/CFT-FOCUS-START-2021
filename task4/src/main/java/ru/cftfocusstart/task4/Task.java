package ru.cftfocusstart.task4;

public class Task implements Runnable {
    private final int shiftIter;
    private final int totalThreadsNumber;
    private final int totalIter;

    private double resultValue;

    public Task(int shiftIter, int totalThreadsNumber, int totalIter) {
        this.shiftIter = shiftIter;
        this.totalThreadsNumber = totalThreadsNumber;
        this.totalIter = totalIter;
    }

    @Override
    public void run() {
        // log.debug("Calculations started");
        for (int i = shiftIter; i < totalIter; i += totalThreadsNumber) {
            resultValue += 1. / Math.pow(2, i);
        }
    }

    public double getResultValue() {
        return resultValue;
    }
}
