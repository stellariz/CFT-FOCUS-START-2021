package ru.cftfocusstart.task6.client.UI;

import ru.cftfocusstart.task6.client.NetworkLogic;

public class ConnectionCreator implements ConnectionListener {

    private final NetworkLogic networkLogic;

    public ConnectionCreator(NetworkLogic networkLogic) {
        this.networkLogic = networkLogic;
    }

    @Override
    public void onClickConnect(String serverIP, String serverPort) {
        networkLogic.tryToConnectToServer(serverIP, Integer.parseInt(serverPort));
    }
}
