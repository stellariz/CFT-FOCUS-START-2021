package ru.cftfocusstart.task6.server;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import ru.cftfocusstart.task6.client.ChatUser;
import ru.cftfocusstart.task6.client.Message.Message;
import ru.cftfocusstart.task6.client.Message.MessageType;
import ru.cftfocusstart.task6.common.IOTools;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.stream.Stream;

@Slf4j
public class Server {
    private static final int port;

    private final Map<Socket, ChatUser> clients;
    private final Set<String> reservedNames;

    static {
        try (InputStream is = Server.class.getClassLoader().getResourceAsStream("server.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            port = Integer.parseInt(properties.getProperty("port"));
        } catch (IOException e) {
            throw new IllegalArgumentException("Incorrect properties file", e);
        }
    }

    public static void main(String[] args) {
        new Server().startServer();
    }

    public Server() {
        clients = new LinkedHashMap<>();
        reservedNames = new HashSet<>();
        reservedNames.add("SERVER");
    }

    private void startServer() {
        Thread clientProcessingThread = new Thread(this::processClients);
        clientProcessingThread.setDaemon(true);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            clientProcessingThread.start();

            while (true) {
                Socket socket = serverSocket.accept();

                synchronized (clients) {
                    clients.put(socket, new ChatUser());
                }
            }
        } catch (IOException e) {
            log.error("Server's crashed: {}", e.getMessage(), e);
            throw new IllegalArgumentException(e);
        }
    }

    private void processClients() {
        while (true) {
            Set<Socket> clients;
            synchronized (this.clients) {
                clients = new HashSet<>(this.clients.keySet());
            }
            for (Socket client : clients) {
                try {
                    Message msg;
                    if ((msg = IOTools.readFromSocket(client.getInputStream())) != null) {
                        switch (msg.getMessageType()) {
                            case GREETING:
                                if (doesUserNameAvailable(msg.getNickName())) {
                                    sendUnsuccessfulConnection(client);
                                } else {
                                    ChatUser chatUser = new ChatUser(msg.getNickName());
                                    this.clients.put(client, chatUser);
                                    sendUserGreetingMessage(client, chatUser);
                                    broadcastNewUserMessage(clients, chatUser);
                                }
                                break;
                            case TEXT:
                                sendUserMessage(clients, msg);
                                break;
                            case DISCONNECT:
                                Set<Socket> leftUsers;
                                ChatUser chatUser = new ChatUser(msg.getNickName());
                                synchronized (this.clients) {
                                    this.clients.remove(client);
                                    leftUsers = this.clients.keySet();
                                }
                                broadcastDisconnectMessage(leftUsers, chatUser);
                                break;
                            default:
                                // игнорируем сообщения других типов от пользователей
                                break;
                        }
                    }
                } catch (IOException e) {
                    log.error("Can't read message from user: {}", e.getMessage(), e);
                    synchronized (this.clients) {
                        this.clients.remove(client);
                    }
                }
            }
        }
    }

    private void sendUserGreetingMessage(Socket client, ChatUser chatUser) {
        try {
            Message msg = generateMessageFromServer(chatUser.getUserName() + " connected to chat!",
                    MessageType.GREETING);
            // передаём список онлайн юзеров новому пользователю
            msg.setChatUserList(new ArrayList<>(clients.values()));
            IOTools.writeInSocket(client.getOutputStream(), msg);
        } catch (IOException e) {
            log.error("Can't send greeting message: {}", e.getMessage(), e);
            synchronized (this.clients) {
                this.clients.remove(client);
            }
        }
    }

    private boolean doesUserNameAvailable(String newUserName) {
        Stream<ChatUser> chatUserStream;
        synchronized (this.clients) {
            chatUserStream = this.clients.values().stream();
        }
        return newUserName.isEmpty() ||
                newUserName.isBlank() ||
                chatUserStream.anyMatch(user -> Objects.equals(user.getUserName(), newUserName)) ||
                reservedNames.contains(newUserName);
    }

    private void broadcastNewUserMessage(Set<Socket> clients, ChatUser chatUser) {
        for (Socket client : clients) {
            try {
                Message msg = generateMessageFromServer(chatUser.getUserName() + " connected to chat!",
                        MessageType.NEW_USER);
                msg.setChatUser(chatUser);
                IOTools.writeInSocket(client.getOutputStream(), msg);
            } catch (IOException e) {
                log.error("Can't send new user message: {}", e.getMessage(), e);
                synchronized (this.clients) {
                    this.clients.remove(client);
                }
            }
        }
    }

    private void broadcastDisconnectMessage(Set<Socket> clients, ChatUser chatUser) {
        for (Socket client : clients) {
            try {
                Message msg = generateMessageFromServer(chatUser.getUserName() + " disconnected from chat",
                        MessageType.DISCONNECT);
                msg.setChatUser(chatUser);
                IOTools.writeInSocket(client.getOutputStream(), msg);
            } catch (IOException e) {
                log.error("Can't send disconnect message: {}", e.getMessage(), e);
                synchronized (this.clients) {
                    this.clients.remove(client);
                }
            }
        }
    }

    private void sendUnsuccessfulConnection(Socket client) {
        try {
            Message msg = new Message();
            msg.setMessageType(MessageType.UNAVAILABLE_NICK);
            IOTools.writeInSocket(client.getOutputStream(), msg);
        } catch (IOException e) {
            log.error("Can't send message about unsuccessful connection: {}", e.getMessage(), e);
            synchronized (this.clients) {
                this.clients.remove(client);
            }
        }
    }

    private void sendUserMessage(Set<Socket> clients, Message msg) {
        for (Socket client : clients) {
            try {
                IOTools.writeInSocket(client.getOutputStream(), msg);
            } catch (IOException e) {
                log.error("Can't send message to user: {}", e.getMessage(), e);
                synchronized (this.clients) {
                    this.clients.remove(client);
                }
            }
        }
    }

    private Message generateMessageFromServer(String text, MessageType messageType) {
        Message message = new Message();
        message.setNickName("SERVER");
        message.setMessageType(messageType);
        message.setText(text);
        message.setDate(new DateTime(DateTimeZone.UTC).toString("dd-MM-yyyy, hh:mm:ss"));
        return message;
    }
}
