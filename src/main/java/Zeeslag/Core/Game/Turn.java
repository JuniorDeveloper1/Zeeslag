package Zeeslag.Core.Game;

import Zeeslag.Core.Player.Player;

public class Turn {
    private Player currentPlayer;

    public Turn(Player startingPlayer) {
        this.currentPlayer = startingPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchTurn() {
        currentPlayer = (currentPlayer == GameManager.getInstance().getPlayer1()) ? GameManager.getInstance().getPlayer2() : GameManager.getInstance().getPlayer1();
    }
}
