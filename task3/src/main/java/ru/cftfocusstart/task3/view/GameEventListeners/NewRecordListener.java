package ru.cftfocusstart.task3.view.GameEventListeners;

import ru.cftfocusstart.task3.model.Game.RecordsListener;
import ru.cftfocusstart.task3.view.View;

public class NewRecordListener implements GameEventListener {
    private final View view;
    private final RecordsListener recordsListener;

    public NewRecordListener(View view, RecordsListener recordsListener) {
        this.view = view;
        this.recordsListener = recordsListener;
    }

    @Override
    public void update() {
        view.drawRecordsWindow(recordsListener);
    }
}
