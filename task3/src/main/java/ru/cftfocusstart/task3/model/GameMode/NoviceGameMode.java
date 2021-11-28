package ru.cftfocusstart.task3.model.GameMode;

import ru.cftfocusstart.task3.model.Game.Game;
import ru.cftfocusstart.task3.model.Game.GameType;

public class NoviceGameMode extends GameMode {
    public NoviceGameMode(Game game) {
        super(game);
        gameType = GameType.NOVICE;
    }
}
