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
    private Map<GameType, Player> tableOfRecords;
    private final File recordsFile;

    private TableEventListener tableEventListener;

    public RecordsTable(String fileNameWithRecords) {
        recordsFile = new File(fileNameWithRecords);
        try {
            recordsFile.createNewFile();
        } catch (IOException e) {
            log.error("Error in the creating file with records", e);
            throw new IllegalArgumentException();
        }
    }

    public void initTable() {
        tableOfRecords = new HashMap<>(3);
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

    private void updateRecordsTableFromFile(String mode, String name, int score) {
        Player winner = new Player(name, score);
        switch (mode) {
            case "NOVICE":
                tableOfRecords.put(GameType.NOVICE, winner);
                tableEventListener.onUpdateTable(GameType.NOVICE, winner);
                break;
            case "MEDIUM":
                tableOfRecords.put(GameType.MEDIUM, winner);
                tableEventListener.onUpdateTable(GameType.MEDIUM, winner);
                break;
            case "EXPERT":
                tableOfRecords.put(GameType.EXPERT, winner);
                tableEventListener.onUpdateTable(GameType.EXPERT, winner);
                break;
            default:
                throw new IllegalArgumentException("Incorrect file");
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

    public void updateRecord(GameType gameType, String name, int newRecord) {
        tableOfRecords.replace(gameType, new Player(name, newRecord));
        tableEventListener.onUpdateTable(gameType, new Player(name, newRecord));
    }

    public int getScoreByType(GameType gameType) {
        return tableOfRecords.get(gameType).getTimeRecord();
    }

    public Map<GameType, Player> getTableOfRecords() {
        return tableOfRecords;
    }

    public void setTableEventListener(TableEventListener tableEventListener) {
        this.tableEventListener = tableEventListener;
    }
}
