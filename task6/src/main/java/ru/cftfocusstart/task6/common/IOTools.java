package ru.cftfocusstart.task6.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.cftfocusstart.task6.client.Message.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class IOTools {
    private static final int MAX_MESSAGE_SIZE = 500;
    private static final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    private IOTools() {
    }

    public static Message readFromSocket(InputStream socketInputStream) throws IOException {
        int available = socketInputStream.available();
        if (available > 0) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while ((available = socketInputStream.available()) > 0) {
                socketInputStream.read(byteBuffer.array(), 0, available);
                byteArrayOutputStream.write(byteBuffer.array());
            }
            byte[] result = byteArrayOutputStream.toByteArray();
            byteBuffer.clear();
            return new ObjectMapper().readValue(result, Message.class);
        }
        return null;
    }

    public static void writeInSocket(OutputStream socketOutputStream, Message msg) throws IOException {
        byte[] bytes = new ObjectMapper().writeValueAsBytes(msg);
        socketOutputStream.write(bytes);
        socketOutputStream.flush();
    }

    public static int getMaxMessageSize() {
        return MAX_MESSAGE_SIZE;
    }
}
