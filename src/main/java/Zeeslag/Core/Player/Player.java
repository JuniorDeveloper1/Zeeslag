package Zeeslag.Core.Player;

import Zeeslag.Core.Board.Board;
import Zeeslag.Core.Board.Cell;
import Zeeslag.Core.Game.GameManager;
import Zeeslag.Core.Ship.Ship;
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
                //otherplayer.getBoard().getPlacedShips().remove(ship);
                ship.hit();
                gameManager.getTurn().setPlayerTurn(this);
            } else {
                cell.setFill(Color.BLACK);
                opponentCell.setFill(Color.BLACK);
                gameManager.getTurn().setPlayerTurn(otherplayer);
            }

            System.out.println("SIZE: " + otherplayer.getBoard().getPlacedShips().size());
        } else {
            for (int i = 0; i < 100; i++) {
                System.out.println("JE HEBT GEWONNEN!");
            }

        }

        return true;
    }

    public boolean hasWon(Player otherPlayer) {
        for (Ship ship : otherPlayer.getBoard().getPlacedShips()) {
            if (!ship.isSunk()) {
                return false;
            }
        }

        if(otherPlayer.getBoard().getPlacedShips().size() >= 1) {

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

