package ru.cftfocusstart.task3.view.GameRestart;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameType;
import ru.cftfocusstart.task3.model.Field.ConfigField;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class GameNoviceModeCreator extends NewGameModeCreator implements GameRestarter {

    public GameNoviceModeCreator(Game game, MainWindow mainWindow) {
        super(game, mainWindow);
    }

    @Override
    public void restartGame() {
        ConfigField.setSizeOfField(9, 9);
        ConfigField.setTotalBombs(10);
        game.setGameType(GameType.NOVICE);
        super.updateView();
    }
}
