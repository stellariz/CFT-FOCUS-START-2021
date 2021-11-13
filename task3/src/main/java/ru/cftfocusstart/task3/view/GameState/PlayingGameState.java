package ru.cftfocusstart.task3.view.GameState;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameState;
import ru.cftfocusstart.task3.view.Windows.LoseWindow;
import ru.cftfocusstart.task3.view.Windows.MainWindow;
import ru.cftfocusstart.task3.view.Windows.WinWindow;

public class PlayingGameState implements GameStateListener {
    private final Game game;
    private final MainWindow mainWindow;

    public PlayingGameState(Game game, MainWindow mainWindow) {
        this.game = game;
        this.mainWindow = mainWindow;
        game.setGameState(GameState.PLAYING);
    }

    @Override
    public void onChangingGameState() {
        if (!game.isFieldExploded()) {
            WinningGameState winningGameState = new WinningGameState(game, mainWindow);
            game.updateGameState(winningGameState);
            WinWindow winWindow = new WinWindow(mainWindow);
            winWindow.setNewGameListener(e -> winningGameState.onChangingGameState());
            winWindow.setVisible(true);
        } else {
            LosingGameState losingGameState = new LosingGameState(game, mainWindow);
            game.updateGameState(losingGameState);
            LoseWindow loseWindow = new LoseWindow(mainWindow);
            loseWindow.setNewGameListener(e -> losingGameState.onChangingGameState());
            loseWindow.setVisible(true);
        }
    }
}
