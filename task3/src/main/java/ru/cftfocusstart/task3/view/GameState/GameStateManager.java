package ru.cftfocusstart.task3.view.GameState;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameState;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class GameStateManager {
    private final Game game;
    private final MainWindow mainWindow;

    public GameStateManager(Game game, MainWindow mainWindow) {
        this.game = game;
        this.mainWindow = mainWindow;
    }

    public void updateGameState(GameState gameState) {
        game.setGameState(gameState);
        switch (gameState) {
            case LOSING:
                game.setGameStateListener(new LosingGameState(game, mainWindow));
                break;
            case WINNING:
                game.setGameStateListener(new WinningGameState(game, mainWindow));
                break;
        }
        game.updateState();
    }

    public GameState getGameState(){
        return game.getGameState();
    }
}
