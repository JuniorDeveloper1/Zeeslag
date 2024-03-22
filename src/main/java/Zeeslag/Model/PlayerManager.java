package Zeeslag.Model;

import Zeeslag.Model.Core.Board;
import Zeeslag.Model.Core.Player;
import Zeeslag.Model.Core.Ship;
import Zeeslag.Model.GameManager;
import javafx.scene.paint.Color;

public class PlayerManager {
    private final GameManager gameManager = GameManager.getInstance();
    private final Player player1;
    private final Player player2;

    public PlayerManager() {
        this.player1 = gameManager.getPlayer1();
        this.player2 = gameManager.getPlayer2();
        this.player1.setCurrentIndex(0);
        this.player2.setCurrentIndex(0);
    }


    public boolean placeShipHorizontal(Player player, int x, int y) {
        Board board = player.getBoard();
        int currentIndex = player.getCurrentIndex();
        Ship currentShip = null;
        int size = -1;
        int boardSize =-1;


        if (currentIndex < board.getAmountShips()) {
            currentShip = board.getPlacedShips().get(currentIndex);
            currentShip.setVertical(false);
            size = currentShip.getSize();
            boardSize = board.getSizeBoard();
        }
        int maxIndex =  player.getBoard().getAmountShips();
        int currentIndexForErrorCatch = currentIndex + 1;

        if (x + size > boardSize || currentIndexForErrorCatch > maxIndex) {
            return false;
        }

        for (int j = 0; j < size; j++) {
            if (board.getCell(x, y).hasShip()) {
                return false;
            }
        }

        for (int j = 0; j < size; j++) {
            board.placeShip(currentShip, x, y);
        }

        if(!player.getBoard().isAllShipsPlaced()) {
            player.setCurrentIndex(currentIndex + 1);
        }

        return true;
    }

    public boolean placeShipVertical(Player player, int x, int y) {
        Board board = player.getBoard();
        int currentIndex = player.getCurrentIndex();
        Ship currentShip = null;
        int size = -1;
        int boardSize =-1;

        if (currentIndex < board.getAmountShips()) {
            currentShip = board.getPlacedShips().get(currentIndex);
            currentShip.setVertical(true);
            size = currentShip.getSize();
            boardSize = board.getSizeBoard();
        }

        int maxIndex =  player.getBoard().getAmountShips();
        int currentIndexForErrorCatch = currentIndex + 1;

        if (y + size > boardSize || currentIndexForErrorCatch > maxIndex) {
            return false;
        }

        for (int j = 0; j < size; j++) {
            if (board.getCell(x, y).hasShip()) {
                return false;
            }
        }

        for (int j = 0; j < size; j++) {
            board.placeShip(currentShip, x, y);
        }

        if(!player.getBoard().isAllShipsPlaced()) {
            player.setCurrentIndex(currentIndex + 1);
        }

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
