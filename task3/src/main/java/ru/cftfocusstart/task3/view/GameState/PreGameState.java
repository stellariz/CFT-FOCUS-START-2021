package ru.cftfocusstart.task3.view.GameState;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameState;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class PreGameState implements GameStateListener {
    private final Game game;
    private final MainWindow mainWindow;

    public PreGameState(Game game, MainWindow mainWindow) {
        this.game = game;
        this.mainWindow = mainWindow;
        game.createNewField();
        game.setGameState(GameState.PREGAME);
    }

    @Override
    public void onChangingGameState() {
        game.updateGameState(new PlayingGameState(game, mainWindow));
    }
}
