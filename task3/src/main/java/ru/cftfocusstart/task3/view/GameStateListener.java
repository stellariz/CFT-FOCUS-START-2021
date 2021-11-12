package ru.cftfocusstart.task3.view;

import ru.cftfocusstart.task3.model.Game;
import ru.cftfocusstart.task3.model.GameState;

public class GameStateListener {
    private final Game game;
    private final MainWindow mainWindow;

    public GameStateListener(Game game, MainWindow mainWindow) {
        this.game = game;
        this.mainWindow = mainWindow;
    }

    public void winningGame() {
        game.setGameState(GameState.WINNING);
        WinWindow winWindow = new WinWindow(mainWindow);
    }

    public void losingGame() {
        game.setGameState(GameState.LOSING);
        LoseWindow loseWindow = new LoseWindow(mainWindow);
    }
}
