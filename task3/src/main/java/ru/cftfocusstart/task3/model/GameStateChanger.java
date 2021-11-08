package ru.cftfocusstart.task3.model;

import ru.cftfocusstart.task3.view.MainWindow;

public interface GameStateChanger {
    void goNextState(Game game, MainWindow mainWindow);
}
