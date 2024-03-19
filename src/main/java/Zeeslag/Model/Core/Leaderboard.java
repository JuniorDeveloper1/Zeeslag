package Zeeslag.Model.Core;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard {

    private List<PlayerStats> playerStats;
    private final String LEADERBOARD_PATH = "resources/leaderboard/leaderboard.txt";
    private final String GAMEDATA_PATH = "resources/gamedata/playergamedata.txt";

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

    public void updateLeaderboardForPlayer(String playerName) {
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
        writeLeaderboardFile();
    }

    /**
     * Checking every user in the playergamedata.txt file.
     We are splitting the parts because of the name;attacks;date format
     We only need the name parts[0]. We then update the leaderboard,
      if the player is already found in the list
     * @throws FileNotFoundException
     */
    private void loadLeaderBoardFromFile() throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(getGameDataPath()))) {
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String playerName = parts[0];
                    updateLeaderboardForPlayer(playerName);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saving the leaderboard where we write all the users from the list
     * into the leaderboard.txt.
     */
    private void writeLeaderboardFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getLeaderboardpath()))) {
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

    public String getGameDataPath() {
        return GAMEDATA_PATH;
    }

    public String getLeaderboardpath() {
        return LEADERBOARD_PATH;
    }
}
