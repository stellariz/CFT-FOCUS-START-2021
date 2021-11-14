package ru.cftfocusstart.task3.view.GameMode;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameType;
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
                game.setGameMode(new NoviceGameMode(game, mainWindow));
                break;
            case MEDIUM:
                game.setGameMode(new MediumGameMode(game, mainWindow));
                break;
            case EXPERT:
                game.setGameMode(new HardGameMode(game, mainWindow));
                break;
        }
        game.createNewField();
    }
}
