package ru.cftfocusstart.task6.client.Message;

import lombok.Data;

@Data
public class Message {
    private String nickName;
    private String text;
    private MessageType messageType;
    private String date;
}
