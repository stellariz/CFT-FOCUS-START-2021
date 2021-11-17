package ru.cftfocusstart.task3.model.Game;

import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class TimerListener {
    private final MainWindow mainWindow;
    private final GameTimer gameTimer;

    public TimerListener(GameTimer gameTimer, MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.gameTimer = gameTimer;
    }

    public void updateTimerInView() {
        mainWindow.setTimerValue(gameTimer.getSeconds());
    }
}
