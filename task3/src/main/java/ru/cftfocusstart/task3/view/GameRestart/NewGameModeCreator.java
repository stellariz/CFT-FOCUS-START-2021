package ru.cftfocusstart.task3.view.GameRestart;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public abstract class NewGameModeCreator {
    protected final Game game;
    protected final MainWindow mainWindow;

    public NewGameModeCreator(Game game, MainWindow mainWindow) {
        this.game = game;
        this.mainWindow = mainWindow;
    }

    protected void updateView() {
        mainWindow.createGameField();
        mainWindow.repaint();
        mainWindow.pack();
    }
}
