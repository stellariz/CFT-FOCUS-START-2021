package ru.cftfocusstart.task6.client;

import ru.cftfocusstart.task6.client.Message.Message;

public interface ChatUpdater {
    void onBigMessage();

    void onReceiveMessage(Message message);

    void onReceiveUnsuccessfulConnection();

    void onReceiveUnavailableNick();

    void onSuccessfulConnection();

    void onReceiveNewUser(Message message);

    void onReceiveUserDisconnected(Message message);

    void onReceiveGreetingFromServer(Message message);
}
