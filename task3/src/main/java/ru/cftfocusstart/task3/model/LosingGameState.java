package ru.cftfocusstart.task3.model;

import ru.cftfocusstart.task3.view.LoseWindow;
import ru.cftfocusstart.task3.view.MainWindow;

public class LosingGameState implements GameStateChanger{

    @Override
    public void goNextState(Game game, MainWindow mainWindow) {
        game.updateGameState(GameState.LOSING);
        LoseWindow loseWindow = new LoseWindow(mainWindow);
    }

}
