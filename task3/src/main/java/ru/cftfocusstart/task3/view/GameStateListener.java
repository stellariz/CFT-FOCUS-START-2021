package ru.cftfocusstart.task3.view;

import ru.cftfocusstart.task3.model.Game;
import ru.cftfocusstart.task3.model.GameState;

public class GameStateListener {
    private final Game game;
    private final NewGameCreator newGameCreator;

    public GameStateListener(Game game, NewGameCreator newGameCreator) {
        this.game = game;
        this.newGameCreator = newGameCreator;
    }

    public void winningGame() {
        game.setGameState(GameState.WINNING);
        WinWindow winWindow = new WinWindow(newGameCreator.getMainWindow());
        winWindow.setNewGameListener(e -> newGameCreator.restartGame());
        winWindow.setVisible(true);
    }

    public void losingGame() {
        game.setGameState(GameState.LOSING);
        LoseWindow loseWindow = new LoseWindow(newGameCreator.getMainWindow());
        loseWindow.setNewGameListener(e -> newGameCreator.restartGame());
        loseWindow.setVisible(true);
    }
}
