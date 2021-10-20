package ru.cftfocusstart.task2.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;


public final class Reader {
    private static final Path fileName = Path.of("task2/src/main/resources/cfg.txt");

    public static List<String> read() throws IOException{
        List<String> args = new LinkedList<>();
        try(BufferedReader br =
                    Files.newBufferedReader(fileName, StandardCharsets.UTF_8)){
            String line;
            while ((line = br.readLine()) != null){
                args.add(line);
            }
            return args;
        }
    }
}
