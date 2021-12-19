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
            if (message.getText() != null && message.getText().length() > IOTools.getMaxMessageSize()) {
                chatUpdater.onBigMessage();
            } else {
                IOTools.writeInSocket(serverSocket.getOutputStream(), message);
            }
        } catch (IOException e) {
            closeConnection();
            log.error(e.getMessage(), e);
        }
    }

    public void receiveMessagesFromServer() {
        while (true) {
            try {
                Message msg;
                if ((msg = IOTools.readFromSocket(serverSocket.getInputStream())) != null) {
                    switch (msg.getMessageType()) {
                        case GREETING:
                            chatUpdater.onReceiveGreetingFromServer(msg);
                            break;
                        case NEW_USER:
                            if (Objects.equals(msg.getChatUser().getUserName(), client.getNickName())) {
                                chatUpdater.onReceiveMessage(msg);
                            } else {
                                chatUpdater.onReceiveNewUser(msg);
                            }
                            break;
                        case UNAVAILABLE_NICK:
                            chatUpdater.onReceiveUnavailableNick();
                            break;
                        case TEXT:
                            chatUpdater.onReceiveMessage(msg);
                            break;
                        case DISCONNECT:
                            chatUpdater.onReceiveUserDisconnected(msg);
                            break;
                        default:
                            log.info("Unknown message type");
                    }
                }
            } catch (IOException e) {
                closeConnection();
                log.error(e.getMessage(), e);
            }
        }
    }

    public void badConnectionToServer() {
        chatUpdater.onReceiveUnsuccessfulConnection();
    }

    public void checkNicknameOnServer(String nick) {
        try {
            if (serverSocket != null) {
                Message message = new Message();
                message.setMessageType(MessageType.GREETING);
                message.setNickName(nick);
                client.setNickName(nick);
                IOTools.writeInSocket(serverSocket.getOutputStream(), message);
            }
        } catch (IOException e) {
            closeConnection();
            log.error("Failed to check nick on server: {}", e.getMessage(), e);
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
            log.error("Failed to connect to server: {}", e.getMessage(), e);
            closeConnection();
            badConnectionToServer();
        }
    }

    public void closeConnection() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
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
