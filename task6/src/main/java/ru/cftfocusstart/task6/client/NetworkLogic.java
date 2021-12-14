package ru.cftfocusstart.task6.client;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task6.client.Message.Message;
import ru.cftfocusstart.task6.client.Message.MessageType;
import ru.cftfocusstart.task6.common.IOTools;

import java.io.IOException;
import java.net.Socket;

@Slf4j
public class NetworkLogic {
    private final Client client;
    private Socket serverSocket;
    private ChatUpdater chatUpdater;

    public void sendMessageOnServer(String text, MessageType messageType) {
        try {
            Message message = new Message();
            message.setNickName(client.getNickName());
            message.setText(text);
            message.setMessageType(messageType);
            IOTools.writeInSocket(serverSocket.getOutputStream(), message);
        } catch (IOException e) {
        }
    }

    public void receiveMessagesFromServer() {
        while (true) {
            try {
                Message msg;
                if ((msg = IOTools.readFromSocket(serverSocket.getInputStream())) != null) {
                    if (msg.getMessageType() == MessageType.GREETING){
                        chatUpdater.onReceiveAvailableNick();
                        chatUpdater.onReceiveMessage(msg);
                    } else if (msg.getMessageType() == MessageType.UNAVAILABLE_NICK) {
                        chatUpdater.onReceiveUnavailableNick();
                    } else {
                        chatUpdater.onReceiveMessage(msg);
                    }
                }
            } catch (IOException e) {

            }
        }
    }

    public void checkNickNameOnServer(String nick) {
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
            chatUpdater.onReceiveUnsuccessfulConnection();
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
}
