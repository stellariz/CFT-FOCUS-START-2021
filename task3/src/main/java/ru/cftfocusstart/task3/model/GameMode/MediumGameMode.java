package ru.cftfocusstart.task3.model.GameMode;

import ru.cftfocusstart.task3.model.Game.Game;
import ru.cftfocusstart.task3.model.Game.GameType;

public class MediumGameMode extends GameMode {
    public MediumGameMode(Game game) {
        super(game);
        gameType = GameType.MEDIUM;
    }
}
