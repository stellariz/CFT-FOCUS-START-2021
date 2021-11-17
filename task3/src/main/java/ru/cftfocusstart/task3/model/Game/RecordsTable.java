package ru.cftfocusstart.task3.model.Game;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@Slf4j
public class RecordsTable {
    private final Map<GameType, Player> tableOfRecords = new HashMap<>(3);
    private final File recordsFile;

    private RecordsListener recordsListener;

    public RecordsTable(String fileNameWithRecords) {
        recordsFile = new File(fileNameWithRecords);
        try {
            recordsFile.createNewFile();
        } catch (IOException e) {
            log.error("Error in the creating file with records", e);
            throw new IllegalArgumentException();
        }
    }

    public void updateRecord(GameType gameType, String name, int newRecord) {
        tableOfRecords.replace(gameType, new Player(name, newRecord));
        recordsListener.updateRecord(gameType, tableOfRecords.get(gameType));
    }

    public int getScoreByType(GameType gameType) {
        return tableOfRecords.get(gameType).getTimeRecord();
    }

    private void updateRecordsTableFromFile(String mode, String name, int score) {
        switch (mode) {
            case "NOVICE":
                tableOfRecords.put(GameType.NOVICE, new Player(name, score));
                recordsListener.updateRecord(GameType.NOVICE, tableOfRecords.get(GameType.NOVICE));
                break;
            case "MEDIUM":
                tableOfRecords.put(GameType.MEDIUM, new Player(name, score));
                recordsListener.updateRecord(GameType.MEDIUM, tableOfRecords.get(GameType.MEDIUM));
                break;
            case "EXPERT":
                tableOfRecords.put(GameType.EXPERT, new Player(name, score));
                recordsListener.updateRecord(GameType.EXPERT, tableOfRecords.get(GameType.EXPERT));
                break;
            default:
                throw new IllegalArgumentException("Incorrect file");
        }
    }

    public void initTable() {
        tableOfRecords.put(GameType.NOVICE, new Player("Unknown", 999));
        tableOfRecords.put(GameType.MEDIUM, new Player("Unknown", 999));
        tableOfRecords.put(GameType.EXPERT, new Player("Unknown", 999));
        try (Scanner scanner = new Scanner(recordsFile)) {
            while (scanner.hasNextLine()) {
                updateRecordsTableFromFile(scanner.next(), scanner.next(), scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            log.error("File with records wasn't found", e);
            throw new IllegalArgumentException("File with records wasn't found");
        }
    }

    public void uploadFile() {
        try (PrintWriter printWriter = new PrintWriter(recordsFile)) {
            String newTable = tableOfRecords.entrySet()
                    .stream()
                    .map(e -> e.getKey() + " " + e.getValue().getName() + " " + e.getValue().getTimeRecord())
                    .collect(Collectors.joining(System.lineSeparator()));
            printWriter.print(newTable);
        } catch (FileNotFoundException e) {
            log.error("File with records wasn't found", e);
            throw new IllegalArgumentException("File with records wasn't found");
        }
    }

    public void setRecordsListener(RecordsListener recordsListener) {
        this.recordsListener = recordsListener;
    }

    public RecordNameListener getRecordsListener() {
        return recordsListener;
    }
}
