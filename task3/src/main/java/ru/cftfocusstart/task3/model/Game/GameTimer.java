package ru.cftfocusstart.task3.model.Game;

import javax.swing.*;

public class GameTimer {
    private final Timer timer;
    private TimeListener timeListener;

    private int seconds;

    public GameTimer() {
        timer = new Timer(1000, e -> {
            ++seconds;
            if (timeListener != null) {
                timeListener.updateTimerInView();
            }
        });
    }

    public void setTimeListener(TimeListener timeListener) {
        this.timeListener = timeListener;
    }

    public void stopTimer() {
        timer.stop();
    }

    public void resetTimer() {
        stopTimer();
        seconds = 0;
        timeListener.updateTimerInView();
    }

    public void startTimer() {
        timer.start();
    }

    public int getSeconds() {
        return seconds;
    }
}
