package Zeeslag.modulesVerzinBetereNaamXd.Leaderboard;

import java.util.Comparator;
import java.util.Map;

public class PlayerWinsComperator implements Comparator<PlayerStats> {


    @Override
    public int compare(PlayerStats player1, PlayerStats player2) {
        return Integer.compare(player2.getWins(), player1.getWins());
    }
}
