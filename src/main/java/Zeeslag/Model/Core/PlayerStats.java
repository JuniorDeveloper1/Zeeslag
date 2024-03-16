package Zeeslag.Model.Core;

public class PlayerStats {
    /**
     * Making the player stats to store the player stats.
     * Amount of wins and the player name.
     */

    private String playerName;
    private int wins;

    public PlayerStats(String playerName, int wins) {
        this.playerName = playerName;
        this.wins = wins;
    }

    public void increment() {
        this.setWins(this.getWins() + 1);
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getWins() {
        return wins;
    }

    private void setWins(int wins) {
        this.wins = wins;
    }
}
