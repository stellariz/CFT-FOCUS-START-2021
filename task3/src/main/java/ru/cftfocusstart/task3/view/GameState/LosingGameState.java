package ru.cftfocusstart.task3.view.GameState;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameState;
import ru.cftfocusstart.task3.view.GameRestart.DefaultRestart;
import ru.cftfocusstart.task3.view.Windows.LoseWindow;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class LosingGameState implements GameStateListener {
    private final Game game;
    private final MainWindow mainWindow;

    public LosingGameState(Game game, MainWindow mainWindow) {
        this.game = game;
        this.mainWindow = mainWindow;
    }

    @Override
    public void onChangingGameState() {
        game.setGameState(GameState.LOSING);
        LoseWindow loseWindow = new LoseWindow(mainWindow);
        loseWindow.setNewGameListener(e -> new DefaultRestart(game, mainWindow).restartGame());
        loseWindow.setVisible(true);
    }
}
