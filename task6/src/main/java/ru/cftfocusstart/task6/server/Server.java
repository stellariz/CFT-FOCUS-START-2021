package ru.cftfocusstart.task6.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task6.client.Message.Message;
import ru.cftfocusstart.task6.client.Message.MessageType;
import ru.cftfocusstart.task6.common.IOTools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

@Slf4j
public class Server {
    private static final int port = 8080;

    private final Map<Socket, String> clients;
    private Thread clientProcessingThread;

//    static {
//        try (InputStream is = Server.class.getClassLoader().getResourceAsStream("server.properties")) {
//            Properties properties = new Properties();
//            properties.load(is);
//            port = Integer.parseInt(properties.getProperty("port"));
//        } catch (IOException e) {
//            throw new IllegalArgumentException("Incorrect properties file", e);
//        }
//    }

    private void startServer() {
        clientProcessingThread = new Thread(this::processClients);
        clientProcessingThread.setDaemon(true);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            //System.out.println(serverSocket.getInetAddress().getCanonicalHostName());
            clientProcessingThread.start();

            while (true) {
                Socket socket = serverSocket.accept();

                synchronized (clients) {
                    clients.put(socket, null);
                }
            }

        } catch (IOException e) {
            log.info("Server's crashed" + e.getMessage(), e);
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
                        if (msg.getMessageType() == MessageType.GREETING) {
                            if (this.clients.containsValue(msg.getNickName())) {
                                sendUnsuccessfulConnection(client);
                            } else {
                                synchronized (this.clients) {
                                    this.clients.put(client, msg.getNickName());
                                }
                                broadcastGreetingMessage(clients, msg.getNickName());
                            }
                        } else {
                            sendUserMessage(clients, msg);
                        }
                    }
                } catch (IOException e) {
                    log.info("Can't read message" + e.getMessage());
                    synchronized (this.clients) {
                        this.clients.remove(client);
                    }
                }
            }
        }
    }

    private void broadcastGreetingMessage(Set<Socket> clients, String nickName) {
        for (Socket client : clients) {
            try {
                Message msg = new Message();
                msg.setNickName("SERVER");
                msg.setText(nickName + " connected to chat!");
                msg.setMessageType(MessageType.GREETING);
                IOTools.writeInSocket(client.getOutputStream(), msg);
            } catch (IOException e) {
                log.info("Can't send greeting message: " + e.getMessage());
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
            log.info("Can't send message about unsuccessful connection" + e.getMessage());
        }
    }

    private void sendUserMessage(Set<Socket> clients, Message msg) {
        for (Socket client : clients) {
            try {
                IOTools.writeInSocket(client.getOutputStream(), msg);
            } catch (IOException e) {
                log.info("Can't send message to user {}" + this.clients.get(client));
                synchronized (this.clients) {
                    this.clients.remove(client);
                }
            }
        }
    }

    public Server() {
        clients = new LinkedHashMap<>();
    }

    public static void main(String[] args) {
        new Server().startServer();
    }
}
