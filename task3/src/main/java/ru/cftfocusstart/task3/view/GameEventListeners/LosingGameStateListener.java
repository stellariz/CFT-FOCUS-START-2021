package ru.cftfocusstart.task3.view.GameEventListeners;

import ru.cftfocusstart.task3.view.View;

public class LosingGameStateListener implements GameEventListener {
    private final View view;

    public LosingGameStateListener(View view) {
        this.view = view;
    }

    @Override
    public void update() {
        view.drawLoseWindow();
    }
}
