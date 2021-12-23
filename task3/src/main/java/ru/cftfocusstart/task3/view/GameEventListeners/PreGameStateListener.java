package ru.cftfocusstart.task3.view.GameEventListeners;

import ru.cftfocusstart.task3.view.View;

public class PreGameStateListener implements GameEventListener {
    private final View view;

    public PreGameStateListener(View view) {
        this.view = view;
    }

    @Override
    public void update() {
        view.updateView();
        if (!view.getMainWindow().isVisible()) {
            view.drawMainWindow();
        }
    }
}
