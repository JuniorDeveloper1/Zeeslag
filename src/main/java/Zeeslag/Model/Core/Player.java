package Zeeslag.Model.Core;

import Zeeslag.Model.GameManager;
import Zeeslag.Model.helper.SceneUtil;
import javafx.scene.paint.Color;

import java.util.UUID;

public class Player {
    private GameManager gameManager = GameManager.getInstance();
    private final String name;
    private final Board board, opponentBoard;
    private final UUID uuid;
    private int currentIndex = 0;

    public Player(String name) {
        this.name = name;
        this.board = new Board();
        this.opponentBoard = new Board();
        this.uuid = generateUUID();
    }

    public boolean attack(Player otherplayer, int x, int y) {
        if (!hasWon(otherplayer)) {
            Cell cell = opponentBoard.getCell(x, y);
            Cell opponentCell = otherplayer.getBoard().getCell(x, y);
            if (opponentCell.hasShip()) {
                Ship ship = opponentCell.getShip();
                cell.setFill(Color.RED);
                opponentCell.setFill(Color.RED);
                ship.hit();
                if (ship.isSunk()) {
                    SceneUtil.showAlert(otherplayer.getName() + " schip is gezonken!",
                            "Schip met grootte " + ship.getSize() + " is gezonken!!");
                }
                gameManager.getTurn().setPlayerTurn(this);
            } else {
                cell.setFill(Color.BLACK);
                opponentCell.setFill(Color.BLACK);
                gameManager.getTurn().setPlayerTurn(otherplayer);
            }
            System.out.println("SIZE: " + otherplayer.getBoard().getPlacedShips().size());
        } else {
            SceneUtil.showAlert(getName() + "WON!", getName() + " Heeft het spel gewonnen!");
        }
        return true;
    }

    public boolean hasWon(Player otherPlayer) {
        for (Ship ship : otherPlayer.getBoard().getPlacedShips()) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        if (currentIndex < 0) {
            currentIndex = 0;
        }

        if (currentIndex >= 5) {
            getBoard().setAllShipsPlaced(true);
        }
        this.currentIndex = currentIndex;
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public Board getOpponentBoard() {
        return opponentBoard;
    }

    public UUID getUuid() {
        return uuid;
    }

    private UUID generateUUID() {
        return UUID.randomUUID();
    }
}
