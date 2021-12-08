package ru.cftfocusstart.task6.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.cftfocusstart.task6.client.Message.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOTools {

    private IOTools() {
    }

    public static Message readFromSocket(InputStream socketInputStream) throws IOException {
        int available = socketInputStream.available();
        if (available > 0) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            for (; (available = socketInputStream.available()) > 0; ) {
                socketInputStream.read(bytes, 0, available);
                byteArrayOutputStream.write(bytes);
            }
            byte[] result = byteArrayOutputStream.toByteArray();
            return new ObjectMapper().readValue(result, Message.class);
        }
        return null;
    }

    public static void writeInSocket(OutputStream socketOutputStream, Message msg) throws IOException {
        byte[] bytes = new ObjectMapper().writeValueAsBytes(msg);
        socketOutputStream.write(bytes);
        socketOutputStream.flush();
    }
}
