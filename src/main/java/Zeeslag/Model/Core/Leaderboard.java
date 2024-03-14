package Zeeslag.Model.Core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Leaderboard {
    /**
     * Leaderboard will update every 2 minutes
     */

    private List<PlayerStats> playerStats;

    private final String filePath;

    public Leaderboard() throws FileNotFoundException {
        filePath = "resources/leaderboard/leaderboard.txt";
        this.playerStats = new ArrayList<>();
        this.loadLeaderBoardFromFile();
        this.updateLeaderboard();
    }

    private void loadLeaderBoardFromFile() throws FileNotFoundException {
        File file = new File(getFilePath());
        if (!file.exists()) {
            System.out.println("File doesn't exist!");
            return;
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length == 2) {
                /**
                 * Loading every player in the leaderboard
                 */
                // Player == parts[0]
                // Wins == [1]
                getPlayerStats().add(new PlayerStats(parts[0], Integer.parseInt(parts[1]))); // Player - He's wins
            }
        }
        scanner.close();
    }

    public void incrementWins(Player player) {
        String playerName = player.getName();
        boolean playerFound = false;
        for (PlayerStats stats : getPlayerStats()) {
            if (stats.getPlayerName().equals(playerName)) {
                stats.increment();
                playerFound = true;
                break;
            }
        }
        if (!playerFound) {
            getPlayerStats().add(new PlayerStats(playerName, 1));
        }
    }

    public void updateLeaderboard() {
         this.sortPlayerByWins();
    }

    public int getPlayerWins(String playerName) {
        for (PlayerStats stats : getPlayerStats()) {
            if (stats.getPlayerName().equals(playerName)) {
                return stats.getWins();
            }
        }
        return 0;
    }

    public void showLeaderboardToConsole() {
        List<PlayerStats> playerStats = new ArrayList<>(getPlayerStats());
        if(getPlayerStats().isEmpty()) {
            System.out.println("Leaderboard is empty!");
            return;
        }

        for (int i = 0; i < playerStats.size(); i++) {
            PlayerStats player = playerStats.get(i);
            if(i < 10) {
                System.out.print("Nr: " + (i+1) + " ");
                System.out.print("Naam: " + player.getPlayerName() + " ");
                System.out.println("Wins: " + player.getWins());
            }
        }
    }


    private void sortPlayerByWins() {
        getPlayerStats().sort(new PlayerWinsComperator());
    }

    public List<PlayerStats> getPlayerStats() {
        return playerStats;
    }

    public String getFilePath() {
        return filePath;
    }
}
