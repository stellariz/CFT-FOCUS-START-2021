package ru.cftfocusstart.task3.model;

import ru.cftfocusstart.task3.view.MainWindow;

public class PreGameState implements GameStateChanger{
    @Override
    public void goNextState(Game game, MainWindow mainWindow) {
        game.setGameState(new PlayingGameState());
    }
}
