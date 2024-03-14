package Zeeslag.Model;

import Zeeslag.Model.Core.Board;
import Zeeslag.Model.Core.Player;
import Zeeslag.Model.Core.Ship;
import Zeeslag.Model.GameManager;
import javafx.scene.paint.Color;

public class PlayerManager {
    private GameManager gameManager = GameManager.getInstance();
    private Player player1;
    private Player player2;

    public PlayerManager(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        initializePlayers();
    }

    private void initializePlayers() {
        player1.getBoard().setAllShipsPlaced(false);
        player2.getBoard().setAllShipsPlaced(false);
    }

    public boolean placeShipHorizontal(Player player, int x, int y) {
        Board board = player.getBoard();
        int currentIndex = player.getCurrentIndex();
        Ship currentShip = board.getPlacedShips().get(currentIndex);
        int size = currentShip.getSize();
        currentShip.setVertical(false);

        if (x + size > 10 || currentIndex >= 5) {
            return false;
        }

        for (int j = 0; j < size; j++) {
            if (board.getCell(x + j, y).hasShip()) {
                return false;
            }
        }

        for (int j = 0; j < size; j++) {
            board.placeShip(currentShip, x + j, y);
        }

        player.setCurrentIndex(currentIndex + 1);
        return true;
    }

    public boolean placeShipVertical(Player player, int x, int y) {
        Board board = player.getBoard();
        int currentIndex = player.getCurrentIndex();
        Ship currentShip = board.getPlacedShips().get(currentIndex);
        int size = currentShip.getSize();
        currentShip.setVertical(true);

        if (y + size > 10 || currentIndex >= 5) {
            return false;
        }

        for (int j = 0; j < size; j++) {
            if (board.getCell(x, y + j).hasShip()) {
                return false;
            }
        }

        for (int j = 0; j < size; j++) {
            board.placeShip(currentShip, x, y + j);
        }

        currentShip.setVertical(false);
        player.setCurrentIndex(currentIndex + 1);
        return true;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

}
