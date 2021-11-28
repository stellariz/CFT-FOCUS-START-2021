package ru.cftfocusstart.task3.model.Game;

public class RecordsListener implements RecordNameListener {
    private final Game game;

    public RecordsListener(Game game) {
        this.game = game;
    }

    @Override
    public void onRecordNameEntered(String name) {
        game.updateRecord(new Player(name, game.getGameDuration()));
    }
}
