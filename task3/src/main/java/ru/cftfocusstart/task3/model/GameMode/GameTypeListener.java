package ru.cftfocusstart.task3.model.GameMode;

import ru.cftfocusstart.task3.model.Game.GameType;

public interface GameTypeListener {
    void onGameTypeChanged(GameType gameType);
    void restartGame();
}
