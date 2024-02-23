package Zeeslag.modulesVerzinBetereNaamXd.Player;

import Zeeslag.modulesVerzinBetereNaamXd.Player.PlayerStats;

import java.util.Comparator;

public class PlayerWinsComperator implements Comparator<PlayerStats> {
    @Override
    public int compare(PlayerStats player1, PlayerStats player2) {
        return Integer.compare(player2.getWins(), player1.getWins());
    }
}
