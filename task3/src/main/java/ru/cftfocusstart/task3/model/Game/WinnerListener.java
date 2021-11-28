package ru.cftfocusstart.task3.model.Game;

import ru.cftfocusstart.task3.view.View;

public class WinnerListener implements GameEventListener{
    private final View view;
    private final RecordsListener recordsListener;

    public WinnerListener(View view, RecordsListener recordsListener) {
        this.view = view;
        this.recordsListener = recordsListener;
    }

    @Override
    public void update() {
        view.drawRecordsWindow(recordsListener);
    }
}
