package ru.cftfocusstart.task3.model.Game;

import ru.cftfocusstart.task3.view.Windows.HighScoresWindow;

public class RecordsListener implements RecordNameListener {
    private final Game game;
    private final HighScoresWindow highScoresWindow;
    private final RecordsTable recordsTable;

    public RecordsListener(Game game, HighScoresWindow highScoresWindow, RecordsTable recordsHolder) {
        this.game = game;
        this.highScoresWindow = highScoresWindow;
        this.recordsTable = recordsHolder;
    }

    public void updateRecord(GameType gameType, Player player) {
        switch (gameType) {
            case NOVICE:
                highScoresWindow.setNoviceRecord(player.getName(), player.getTimeRecord());
                break;
            case MEDIUM:
                highScoresWindow.setMediumRecord(player.getName(), player.getTimeRecord());
                break;
            case EXPERT:
                highScoresWindow.setExpertRecord(player.getName(), player.getTimeRecord());
                break;
            default:
                throw new IllegalArgumentException("Unknown game type");
        }
    }

    @Override
    public void onRecordNameEntered(String name) {
        recordsTable.updateRecord(game.getGameType(), name, game.getGameDuration());
        recordsTable.uploadFile();
    }
}
