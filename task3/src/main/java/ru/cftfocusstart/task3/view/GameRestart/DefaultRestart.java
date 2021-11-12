package ru.cftfocusstart.task3.view.GameRestart;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class DefaultRestart extends NewGameModeCreator implements GameRestarter {

    public DefaultRestart(Game game, MainWindow mainWindow) {
        super(game, mainWindow);
    }

    @Override
    public void restartGame() {
        game.generateField();
        mainWindow.closeCells();
    }
}
