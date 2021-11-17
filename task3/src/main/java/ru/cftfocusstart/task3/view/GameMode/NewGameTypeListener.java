package ru.cftfocusstart.task3.view.GameMode;

import ru.cftfocusstart.task3.model.Game.Game;
import ru.cftfocusstart.task3.model.Game.GameType;
import ru.cftfocusstart.task3.model.Field.ConfigField;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class NewGameTypeListener implements GameTypeListener {
    private final Game game;
    private final MainWindow mainWindow;

    public NewGameTypeListener(Game game, MainWindow mainWindow) {
        this.game = game;
        this.mainWindow = mainWindow;
    }

    @Override
    public void onGameTypeChanged(GameType gameType) {
        switch (gameType) {
            case NOVICE:
                ConfigField.setSizeOfField(9, 9);
                ConfigField.setTotalBombs(10);
                game.setGameMode(new NoviceGameMode(game, mainWindow));
                break;
            case MEDIUM:
                ConfigField.setSizeOfField(16, 16);
                ConfigField.setTotalBombs(40);
                game.setGameMode(new MediumGameMode(game, mainWindow));
                break;
            case EXPERT:
                ConfigField.setSizeOfField(16, 30);
                ConfigField.setTotalBombs(99);
                game.setGameMode(new HardGameMode(game, mainWindow));
                break;
        }
    }
}
