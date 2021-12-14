package ru.cftfocusstart.task6.client;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task6.client.Message.MessageCreator;
import ru.cftfocusstart.task6.client.UI.ConnectionCreator;
import ru.cftfocusstart.task6.client.UI.UserNameUpdater;
import ru.cftfocusstart.task6.client.UI.View;

@Slf4j
public class Client {
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public static void main(String[] args) {
        NetworkLogic networkLogic = new NetworkLogic(new Client());
        ConnectionCreator connectionCreator = new ConnectionCreator(networkLogic);
        UserNameUpdater userNameUpdater = new UserNameUpdater(networkLogic);
        MessageCreator messageCreator = new MessageCreator(networkLogic);

        View view = new View();
        ViewListener messageListener = new ViewListener(view);
        networkLogic.setChatUpdater(messageListener);
        view.setConnectionListener(connectionCreator);
        view.setNameListener(userNameUpdater);
        view.setSendMessageListener(messageCreator);

        view.showServerInfoWindow();
    }
}
