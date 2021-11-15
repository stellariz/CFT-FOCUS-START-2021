package ru.cftfocusstart.task3.view;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameType;
import ru.cftfocusstart.task3.view.Windows.HighScoresWindow;

public class RecordsViewListener implements RecordNameListener {
    private final Game game;
    private final HighScoresWindow highScoresWindow;
    private final RecordsHolder recordsHolder;

    public RecordsViewListener(Game game, HighScoresWindow highScoresWindow, RecordsHolder recordsHolder) {
        this.game = game;
        this.highScoresWindow = highScoresWindow;
        this.recordsHolder = recordsHolder;
    }

    public void updateRecord(GameType gameType, Player player) {
        switch (gameType) {
            case NOVICE:
                highScoresWindow.setNoviceRecord(player.getName(), player.getRecord());
                break;
            case MEDIUM:
                highScoresWindow.setMediumRecord(player.getName(), player.getRecord());
                break;
            case EXPERT:
                highScoresWindow.setExpertRecord(player.getName(), player.getRecord());
                break;
        }
    }

    @Override
    public void onRecordNameEntered(String name) {
        recordsHolder.updateRecord(game.getGameType(), name, game.getTime());
        //recordsHolder.uploadFile();
    }
}
