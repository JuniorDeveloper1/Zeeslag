package Zeeslag.Fx.Model;

import Zeeslag.Core.Leaderboard.Leaderboard;
import Zeeslag.Core.Player.PlayerStats;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class LeaderBoardModel {
    private Leaderboard leaderboard;
    /**
     * Leaderboard will update every 2 minutes
     */
    public LeaderBoardModel() throws FileNotFoundException {
        this.initialize();
    }
    public void initialize() throws FileNotFoundException {
        this.leaderboard = new Leaderboard();
        getLeaderboard().updateLeaderboard();
    }



    public void showLeaderboardToConsole() {
        List<PlayerStats> playerStats = new ArrayList<>(getLeaderboard().getPlayerStats());
        if(getLeaderboard().getPlayerStats().isEmpty()) {
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

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }
}
