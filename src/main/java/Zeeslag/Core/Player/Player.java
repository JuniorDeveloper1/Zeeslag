package Zeeslag.Core.Player;

import Zeeslag.Core.Board.Board;
import Zeeslag.Core.Board.Cell;
import Zeeslag.Core.Game.GameManager;
import Zeeslag.Core.Ship.Ship;
import Zeeslag.Fx.Manager.SceneUtil;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.util.UUID;

public class Player {
    private GameManager gameManager = GameManager.getInstance();
    private final String name;
    private  Board board, opponentBoard;

    private final UUID uuid;

    public Player(String name){
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
                    SceneUtil.showAlert(otherplayer.getName() +" schip is gezonken!",
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
            SceneUtil.showAlert(getName() + "WON!", getName() + " Heeft het spel gewonnen!" );
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




    /**private boolean hasWon(Player otherPlayer) {
     List<Ship> opponentPieces = otherPlayer.getBoard().getPieces();

     int totalPieces = 0;
     for (int i = 0; i < opponentPieces.size(); i++) {
     totalPieces += opponentPieces.get(i).getHeight() * opponentPieces.get(i).getWidth();
     }

     List<Bomb> bombs = otherPlayer.getBoard().getBombs();

     int totalHits = 0;
     for (int i = 0; i < bombs.size(); i++) {
     if (bombs.get(i).isHit()) {
     totalHits++;
     }
     }

     if (totalHits >= totalPieces) {
     System.out.println(this.getName() + " Has won!");
     //Player has won
     return true;
     }

     return false;
     }**/

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public Board getOpponentBoard() {
        return opponentBoard;
    }

    public void setOpponentBoard(Board opponentBoard) {
        this.opponentBoard = opponentBoard;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public UUID getUuid() {
        return uuid;
    }

    private UUID generateUUID() {
        return UUID.randomUUID();
    }

}

