package Zeeslag.fx.Model;

import Zeeslag.fx.Manager.MVPModel;
import Zeeslag.modulesVerzinBetereNaamXd.Leaderboard.Leaderboard;
import Zeeslag.modulesVerzinBetereNaamXd.Leaderboard.PlayerStats;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeaderBoardModel implements MVPModel {
    Leaderboard leaderboard;
    /**
     * Leaderboard will update every 2 minutes
     */
    public LeaderBoardModel() throws FileNotFoundException {
        this.initialize();
        this.loadPresenters();
    }

    @Override
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

    @Override
    public void loadPresenters() {
        String RESET = "\u001B[0m";
        String GREEN = "\u001B[32m";
        String RED = "\u001B[31m";

        try {
            /** TODO: Load all presenter classes here! **/

            System.out.println(GREEN + " Presenters succesfully loaded " + RESET);
        }catch (NullPointerException e) {
            System.out.println(RED + "Presenters Failed to load" + RESET);
            throw new NullPointerException();
        }
    }
}
