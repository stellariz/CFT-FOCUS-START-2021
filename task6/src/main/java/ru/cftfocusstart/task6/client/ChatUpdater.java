package ru.cftfocusstart.task6.client;

import ru.cftfocusstart.task6.client.Message.Message;

public interface ChatUpdater {
    void onReceiveMessage(Message message);

    void onReceiveUnsuccessfulConnection();

    void onReceiveUnavailableNick();

    void onSuccessfulConnection();

    void onReceiveAvailableNick();
}
