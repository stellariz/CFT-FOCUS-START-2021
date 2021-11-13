package ru.cftfocusstart.task3.view.GameState;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameState;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class LosingGameState implements GameStateListener {
    private final Game game;
    private final MainWindow mainWindow;

    public LosingGameState(Game game, MainWindow mainWindow) {
        this.game = game;
        this.mainWindow = mainWindow;
        game.setGameState(GameState.LOSING);
    }

    @Override
    public void onChangingGameState() {
        mainWindow.closeCells();
        game.updateGameState(new PreGameState(game, mainWindow));
    }
}
