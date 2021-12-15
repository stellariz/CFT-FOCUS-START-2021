package ru.cftfocusstart.task6.client;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task6.client.Message.Message;
import ru.cftfocusstart.task6.client.Message.MessageType;
import ru.cftfocusstart.task6.common.IOTools;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

@Slf4j
public class NetworkLogic {
    private final Client client;
    private Socket serverSocket;
    private ChatUpdater chatUpdater;

    public void sendMessageOnServer(Message message) {
        try {
            IOTools.writeInSocket(serverSocket.getOutputStream(), message);
        } catch (IOException e) {
        }
    }

    public void receiveMessagesFromServer() {
        while (true) {
            try {
                Message msg;
                if ((msg = IOTools.readFromSocket(serverSocket.getInputStream())) != null) {
                    // TODO: change on switch
                    if (msg.getMessageType() == MessageType.GREETING){
                        chatUpdater.onReceiveGreetingFromServer(msg);
                    } else if (msg.getMessageType() == MessageType.NEW_USER){
                        if (Objects.equals(msg.getChatUser().getUserName(), client.getNickName())) {
                            chatUpdater.onReceiveMessage(msg);
                        } else {
                            chatUpdater.onReceiveNewUser(msg);
                        }
                    } else if (msg.getMessageType() == MessageType.UNAVAILABLE_NICK) {
                        chatUpdater.onReceiveUnavailableNick();
                    } else if (msg.getMessageType() == MessageType.DISCONNECT){
                        chatUpdater.onReceiveUserDisconnected(msg);
                    } else {
                        chatUpdater.onReceiveMessage(msg);
                    }
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public void badConnectionToServer(){
        chatUpdater.onReceiveUnsuccessfulConnection();
    }

    public void checkNicknameOnServer(String nick) {
        if (serverSocket != null) {
            Message message = new Message();
            message.setMessageType(MessageType.GREETING);
            message.setNickName(nick);
            client.setNickName(nick);
            try  {
                IOTools.writeInSocket(serverSocket.getOutputStream(), message);
            } catch (IOException e) {
                log.error("Failed to check nick on server: " + e.getMessage(), e);
            }
        }
    }

    public void tryToConnectToServer(String ipAddress, int port) {
        try {
            serverSocket = new Socket(ipAddress, port);
            Thread tmp = new Thread(this::receiveMessagesFromServer);
            tmp.setDaemon(true);
            tmp.start();
            chatUpdater.onSuccessfulConnection();
        } catch (IOException e) {
            log.error("Failed to connect to server: " + e.getMessage(), e);
            badConnectionToServer();
        }
    }

    public void closeConnection() {
        try {
            serverSocket.close();
        } catch (IOException e) {

        }
    }

    public NetworkLogic(Client client) {
        this.client = client;
    }

    public void setChatUpdater(ChatUpdater chatUpdater) {
        this.chatUpdater = chatUpdater;
    }

    public Client getClient() {
        return client;
    }
}
