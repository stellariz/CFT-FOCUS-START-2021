package ru.cftfocusstart.task3.model.Game;

import ru.cftfocusstart.task3.view.GameEventListeners.GameEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameEventManager {
    Map<String, List<GameEventListener>> listeners = new HashMap<>();

    public GameEventManager(String... events) {
        for (String event : events) {
            this.listeners.put(event, new ArrayList<>());
        }
    }

    public void subscribe(String event, GameEventListener listener) {
        List<GameEventListener> users = listeners.get(event);
        users.add(listener);
    }

    public void unsubscribe(String event, GameEventListener listener) {
        List<GameEventListener> users = listeners.get(event);
        users.remove(listener);
    }

    public void notify(String event) {
        List<GameEventListener> users = listeners.get(event);
        for (GameEventListener listener : users) {
            listener.update();
        }
    }
}
