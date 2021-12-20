package ru.cftfocusstart.task6.client.Message;

import lombok.Data;
import ru.cftfocusstart.task6.client.ChatUser;

import java.util.List;

@Data
public class Message {
    private String nickName;
    private String text;
    private MessageType messageType;
    private String date;
    private ChatUser chatUser;
    private List<ChatUser> chatUserList;
}
