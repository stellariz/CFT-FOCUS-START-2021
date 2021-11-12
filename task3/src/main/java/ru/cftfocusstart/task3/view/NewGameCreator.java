package ru.cftfocusstart.task3.view;

import ru.cftfocusstart.task3.model.ConfigField;
import ru.cftfocusstart.task3.model.Game;
import ru.cftfocusstart.task3.model.GameState;

public class NewGameCreator {
    private final Game game;
    private final MainWindow mainWindow;

    public NewGameCreator(Game game, MainWindow mainWindow) {
        this.game = game;
        this.mainWindow = mainWindow;
    }

    public void restartGame() {
        game.setGameState(GameState.PLAYING);
        game.restartGame();
        mainWindow.setVisible(false);
        mainWindow.createGameField();
        mainWindow.setVisible(true);
    }

    public void startNoviceGame() {
        if (game.getGameType() == GameType.NOVICE) {
            mainWindow.closeCells();
        } else {
            ConfigField.setSizeOfField(9, 9);
            ConfigField.setTotalBombs(10);
            game.setGameType(GameType.NOVICE);
            restartGame();
        }
    }

    public void startMediumGame() {
        if (game.getGameType() == GameType.MEDIUM) {
            mainWindow.closeCells();
        } else {
            ConfigField.setSizeOfField(16, 16);
            ConfigField.setTotalBombs(40);
            game.setGameType(GameType.MEDIUM);
            restartGame();
        }
    }

    public void startHardGame() {
        if (game.getGameType() == GameType.EXPERT) {
            mainWindow.closeCells();
        } else {
            ConfigField.setSizeOfField(16, 30);
            ConfigField.setTotalBombs(99);
            game.setGameType(GameType.EXPERT);
            restartGame();
        }
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }
}
