package ru.cftfocusstart.task6.client.Message;

import ru.cftfocusstart.task6.client.NetworkLogic;
import ru.cftfocusstart.task6.client.UI.SendMessageListener;

public class MessageCreator implements SendMessageListener {
    private final NetworkLogic networkLogic;

    public MessageCreator(NetworkLogic networkLogic) {
        this.networkLogic = networkLogic;
    }

    @Override
    public void onClickSend(String text) {
        networkLogic.sendMessageOnServer(text, MessageType.TEXT);
    }

    @Override
    public void onClickClose() {
        networkLogic.sendMessageOnServer(null, MessageType.DISCONNECT);
    }
}
