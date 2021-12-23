package ru.cftfocusstart.task3.model.Game;

public class GameTimerController implements TimerController {
    private final GameTimer gameTimer;

    public GameTimerController(GameTimer gameTimer) {
        this.gameTimer = gameTimer;
    }

    @Override
    public int getTime() {
        return gameTimer.getSeconds();
    }

    @Override
    public void stopTimer() {
        gameTimer.stopTimer();
    }

    @Override
    public void resetTimer() {
        gameTimer.resetTimer();
    }

    @Override
    public void startTimer() {
        gameTimer.startTimer();
    }
}
