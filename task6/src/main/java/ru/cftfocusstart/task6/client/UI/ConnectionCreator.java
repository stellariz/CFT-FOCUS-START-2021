package ru.cftfocusstart.task6.client.UI;

import ru.cftfocusstart.task6.client.NetworkLogic;

public class ConnectionCreator implements ConnectionListener {
    private final NetworkLogic networkLogic;

    public ConnectionCreator(NetworkLogic networkLogic) {
        this.networkLogic = networkLogic;
    }

    private void checkArgs(String serverIp, String serverPort) {
        if (serverIp == null || serverPort == null){
            throw new IllegalArgumentException("Parametres can't be null!");
        }
        try {
            Integer.parseInt(serverPort);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Server port's incorrect!");
        }
    }

    @Override
    public void onClickConnect(String serverIP, String serverPort) {
        try {
            checkArgs(serverIP, serverPort);
            networkLogic.tryToConnectToServer(serverIP, Integer.parseInt(serverPort));
        } catch (IllegalArgumentException e){
            networkLogic.badConnectionToServer();
        }
    }
}
