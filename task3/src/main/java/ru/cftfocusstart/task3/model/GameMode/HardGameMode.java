package ru.cftfocusstart.task3.model.GameMode;

import ru.cftfocusstart.task3.model.Game.Game;
import ru.cftfocusstart.task3.model.Game.GameType;

public class HardGameMode extends GameMode {
    public HardGameMode(Game game) {
        super(game);
        gameType = GameType.EXPERT;
    }
}
