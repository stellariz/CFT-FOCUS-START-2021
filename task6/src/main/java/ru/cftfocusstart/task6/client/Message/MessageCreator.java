package ru.cftfocusstart.task6.client.Message;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import ru.cftfocusstart.task6.client.NetworkLogic;
import ru.cftfocusstart.task6.client.UI.SendMessageListener;

public class MessageCreator implements SendMessageListener {
    private final String patternOfDate = "dd-MM-yyyy, hh:mm:ss";
    private final NetworkLogic networkLogic;

    public MessageCreator(NetworkLogic networkLogic) {
        this.networkLogic = networkLogic;
    }

    @Override
    public void onClickSend(String text) {
        Message message = new Message();
        message.setNickName(networkLogic.getClient().getNickName());
        message.setText(text);
        message.setDate(new DateTime(DateTimeZone.UTC).toString(patternOfDate));
        message.setMessageType(MessageType.TEXT);
        networkLogic.sendMessageOnServer(message);
    }

    @Override
    public void onClickClose() {
        Message message = new Message();
        message.setNickName(networkLogic.getClient().getNickName());
        message.setDate(new DateTime(DateTimeZone.UTC).toString(patternOfDate));
        message.setMessageType(MessageType.DISCONNECT);
        networkLogic.sendMessageOnServer(message);
        networkLogic.closeConnection();
    }
}
