package ru.cftfocusstart.task3.model;

import ru.cftfocusstart.task3.view.MainWindow;

public class WinningGameState implements GameStateChanger{

    @Override
    public void goNextState(Game game, MainWindow mainWindow) {
        game.updateGameState(GameState.WINNING);
    }

}
