package ru.cftfocusstart.task3.view.GameRestart;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameType;
import ru.cftfocusstart.task3.model.Field.ConfigField;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class GameHardModeCreator extends NewGameModeCreator implements GameRestarter {

    public GameHardModeCreator(Game game, MainWindow mainWindow) {
        super(game, mainWindow);
    }

    @Override
    public void restartGame() {
        ConfigField.setSizeOfField(16, 30);
        ConfigField.setTotalBombs(99);
        game.setGameType(GameType.EXPERT);
        super.updateView();
    }
}
