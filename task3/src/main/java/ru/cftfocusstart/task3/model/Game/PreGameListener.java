package ru.cftfocusstart.task3.model.Game;

import ru.cftfocusstart.task3.view.View;

public class PreGameListener implements GameEventListener {

    private final View view;

    public PreGameListener(View view) {
        this.view = view;
    }

    @Override
    public void update() {
        view.updateView();
        view.drawMainWindow();
    }
}
