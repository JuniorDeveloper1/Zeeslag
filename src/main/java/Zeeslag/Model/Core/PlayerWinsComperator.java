package Zeeslag.Model.Core;

import java.util.Comparator;

public class PlayerWinsComperator implements Comparator<PlayerStats> {
    /**
     * Comparing the wins for result that the highest wins is above and the lowest below
     *
     * @param player1 the first object to be compared.
     * @param player2 the second object to be compared.
     * @return
     */
    @Override
    public int compare(PlayerStats player1, PlayerStats player2) {
        return Integer.compare(player2.getWins(), player1.getWins());
    }
}
