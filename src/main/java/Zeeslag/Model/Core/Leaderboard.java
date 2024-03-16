package Zeeslag.Model.Core;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard {

    private List<PlayerStats> playerStats;
    private final String FILE_PATH = "resources/leaderboard/leaderboard.txt";
    private final String ORIGIN_PATH = "resources/gamedata/playergamedata.txt";

    public Leaderboard() throws FileNotFoundException {
        this.playerStats = new ArrayList<>();
        this.loadLeaderBoardFromFile();
        this.sortLeaderboard();
    }

    public void updateLeaderboard(String playerName) {
        boolean found = false;
        for (PlayerStats stats : getPlayerStats()) {
            if (stats.getPlayerName().equals(playerName)) {
                stats.increment();
                found = true;
                break;
            }
        }

        if (!found) {
            PlayerStats newPlayer = new PlayerStats(playerName, 1);
            playerStats.add(newPlayer);
        }
        saveLeaderBoard();
    }

    private void loadLeaderBoardFromFile() throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(ORIGIN_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String playerName = parts[0];
                    // We only need to increment wins, so we skip the second part
                    // and date part for now
                    updateLeaderboard(playerName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveLeaderBoard() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (PlayerStats stats : getPlayerStats()) {
                String format = stats.getPlayerName() + ";" + stats.getWins();
                writer.write(format);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sortLeaderboard(){
        getPlayerStats().sort(new PlayerWinsComperator());
    }

    public List<PlayerStats> getPlayerStats() {
        return playerStats;
    }

    public String getFilePath() {
        return FILE_PATH;
    }

    public String getOriginPath() {
        return ORIGIN_PATH;
    }
}
