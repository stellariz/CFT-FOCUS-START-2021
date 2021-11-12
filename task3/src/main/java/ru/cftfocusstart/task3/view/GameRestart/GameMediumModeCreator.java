package ru.cftfocusstart.task3.view.GameRestart;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameType;
import ru.cftfocusstart.task3.model.Field.ConfigField;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class GameMediumModeCreator extends NewGameModeCreator implements GameRestarter{

    public GameMediumModeCreator(Game game, MainWindow mainWindow) {
        super(game, mainWindow);
    }

    @Override
    public void restartGame() {
        ConfigField.setSizeOfField(16, 16);
        ConfigField.setTotalBombs(40);
        game.setGameType(GameType.MEDIUM);
        super.updateView();
    }
}
