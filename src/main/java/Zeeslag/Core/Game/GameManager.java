package Zeeslag.Core.Game;

import Zeeslag.Core.Player.Player;

public class GameManager {
    private Player player1, player2;
    private boolean hasStarted = false;

    private static GameManager gameManager;

    private GameManager() {
    }

    public static GameManager getInstance() {
        if (gameManager == null) {
            gameManager = new GameManager();
        }
        return gameManager;
    }

    public void startGame() {
        player1 = new Player("Player1");
        player2 = new Player("Player2");
        setHasStarted(false);
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public boolean hasStarted() {
        return hasStarted;
    }

    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }
}
