package ru.cftfocusstart.task3.view;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task3.Game.GameType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Slf4j
public class RecordsHolder {
    private final Map<GameType, Player> tableOfRecords = new HashMap<>(3);
    private final String fileNameWithRecords;

    private RecordsViewListener recordsViewListener;

    public RecordsHolder(String fileNameWithRecords) {
        this.fileNameWithRecords = fileNameWithRecords;
    }

    public void updateRecord(GameType gameType, String name, int newRecord) {
        tableOfRecords.replace(gameType, new Player(name, newRecord));
        recordsViewListener.updateRecord(gameType, tableOfRecords.get(gameType));
    }

    public int getScoreByType(GameType gameType) {
        return tableOfRecords.get(gameType).getRecord();
    }

    private void updateTableOfRecord(String mode, String name, int score) {
        switch (mode) {
            case "N":
                tableOfRecords.put(GameType.NOVICE, new Player(name, score));
                recordsViewListener.updateRecord(GameType.NOVICE, tableOfRecords.get(GameType.NOVICE));
                break;
            case "M":
                tableOfRecords.put(GameType.MEDIUM, new Player(name, score));
                recordsViewListener.updateRecord(GameType.MEDIUM, tableOfRecords.get(GameType.MEDIUM));
                break;
            case "E":
                tableOfRecords.put(GameType.EXPERT, new Player(name, score));
                recordsViewListener.updateRecord(GameType.EXPERT, tableOfRecords.get(GameType.EXPERT));
                break;
            default:
                throw new IllegalArgumentException("Incorrect game mode");
        }
    }

    public void initTable() {
        tableOfRecords.put(GameType.NOVICE, new Player("Unknown", 999));
        tableOfRecords.put(GameType.MEDIUM, new Player("Unknown", 999));
        tableOfRecords.put(GameType.EXPERT, new Player("Unknown", 999));
        try (Scanner scanner = new Scanner(new File(fileNameWithRecords))) {
            while (scanner.hasNextLine()) {
                updateTableOfRecord(scanner.next(), scanner.next(), scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            log.error("Can't find a file", e);
            throw new IllegalArgumentException("File with name " + fileNameWithRecords + " wasn't found");
        }
    }

    private String getLetterMode(GameType gameType) {
        switch (gameType) {
            case NOVICE:
                return "N";
            case MEDIUM:
                return "M";
            case EXPERT:
                return "E";
            default:
                throw new IllegalArgumentException("Incorrect game mode");
        }
    }

    public void uploadFile() {
        try (PrintWriter printWriter = new PrintWriter(fileNameWithRecords)) {
            for (var record : tableOfRecords.entrySet()) {
                printWriter.println(getLetterMode(record.getKey()) + " " + record.getValue().getName() + " " +
                        record.getValue().getRecord());
            }
        } catch (FileNotFoundException e) {
            log.debug("Can't find a file", e);
            throw new IllegalArgumentException("File with name " + fileNameWithRecords + " wasn't found");
        }
    }

    public void setRecordsViewListener(RecordsViewListener recordsViewListener) {
        this.recordsViewListener = recordsViewListener;
    }

    public boolean isNewRecordInType(GameType gameType, int potentialNewRecord) {
        return tableOfRecords.get(gameType).getRecord() > potentialNewRecord;
    }

    public RecordsViewListener getRecordsViewListener() {
        return recordsViewListener;
    }
}
