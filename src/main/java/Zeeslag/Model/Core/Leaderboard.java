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

    /**
     * Updating the leaderboard after a user has won.
     * With an increment of 1
     * @param playerName, the player that has won
     */

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

    /**
     * Checking every user in the playergamedata.txt file.
     We are splitting the parts because of the name;attacks;date format
     We only need the name parts[0]. We then update the leaderboard,
      if the player is already found in the list
     * @throws FileNotFoundException
     */
    private void loadLeaderBoardFromFile() throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(ORIGIN_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String playerName = parts[0];
                    updateLeaderboard(playerName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saving the leaderboard where we write all the users from the list
     * into the leaderboard.txt.
     */
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

    /**
     * Sorting the leaderboard from most wins to least.
     */
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
