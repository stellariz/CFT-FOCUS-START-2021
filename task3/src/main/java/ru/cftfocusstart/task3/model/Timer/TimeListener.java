package ru.cftfocusstart.task3.model.Timer;

import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class TimeListener {
    private final MainWindow mainWindow;
    private final GameTimer gameTimer;

    public TimeListener(MainWindow mainWindow, GameTimer gameTimer) {
        this.mainWindow = mainWindow;
        this.gameTimer = gameTimer;
    }

    public void updateTimerInView() {
        mainWindow.setTimerValue(gameTimer.getSeconds());
    }
}
