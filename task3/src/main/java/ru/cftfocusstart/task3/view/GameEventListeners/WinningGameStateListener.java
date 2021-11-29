package ru.cftfocusstart.task3.view.GameEventListeners;

import ru.cftfocusstart.task3.view.View;

public class WinningGameStateListener implements GameEventListener {
    private final View view;

    public WinningGameStateListener(View view) {
        this.view = view;
    }

    @Override
    public void update() {
        view.drawWinWindow();
    }
}
