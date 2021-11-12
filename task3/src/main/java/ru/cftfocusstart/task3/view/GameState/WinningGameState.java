package ru.cftfocusstart.task3.view.GameState;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameState;
import ru.cftfocusstart.task3.view.GameRestart.DefaultRestart;
import ru.cftfocusstart.task3.view.Windows.MainWindow;
import ru.cftfocusstart.task3.view.Windows.WinWindow;

public class WinningGameState implements GameStateListener {
    private final Game game;
    private final MainWindow mainWindow;

    public WinningGameState(Game game, MainWindow mainWindow) {
        this.game = game;
        this.mainWindow = mainWindow;
    }

    @Override
    public void onChangingGameState() {
        game.setGameState(GameState.WINNING);
        WinWindow winWindow = new WinWindow(mainWindow);
        winWindow.setNewGameListener(e -> new DefaultRestart(game, mainWindow).restartGame());
        winWindow.setVisible(true);
    }
}
