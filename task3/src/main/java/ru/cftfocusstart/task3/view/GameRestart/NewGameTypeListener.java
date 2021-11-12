package ru.cftfocusstart.task3.view.GameRestart;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameType;
import ru.cftfocusstart.task3.view.GameState.GameTypeListener;
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
        if (gameType == game.getGameType()) {
            game.setGameRestarter(new DefaultRestart(game, mainWindow));
        } else {
            switch (gameType) {
                case NOVICE:
                    game.setGameRestarter(new GameNoviceModeCreator(game, mainWindow));
                    break;
                case MEDIUM:
                    game.setGameRestarter(new GameMediumModeCreator(game, mainWindow));
                    break;
                case EXPERT:
                    game.setGameRestarter(new GameHardModeCreator(game, mainWindow));
                    break;
            }
        }
        game.restartNewGame();
    }

    public Game getGame() {
        return game;
    }
}
