package Zeeslag.Core.Player;

import Zeeslag.Core.Board.Board;
import Zeeslag.Core.Board.Cell;
import Zeeslag.Core.Bomb.Bomb;
import Zeeslag.Core.Leaderboard.Leaderboard;
import Zeeslag.Core.Ship.Ship;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class Player {
    private final String name;
    private  Board board, opponentBoard;

    private final UUID uuid;

    public Player(String name){
        this.name = name;
        this.board = new Board();
        this.opponentBoard = new Board();
        this.uuid = generateUUID();
    }
    public boolean attack(Player otherplayer, int x, int y){
        Cell cell = opponentBoard.getCell(x,y);
        Cell opponentCell = otherplayer.getBoard().getCell(x, y);
        if(opponentCell.hasShip()) {
            Ship ship = opponentCell.getShip();
            if(ship.isAlive()) {
                cell.setFill(Color.RED);
                opponentCell.setFill(Color.RED);
                ship.hit();
            }else {
                opponentBoard.getPlacedShips().remove(ship);
                System.out.println("Ship destroyed");
            }
        }else {
            cell.setFill(Color.BLACK);
            opponentCell.setFill(Color.BLACK);
        }
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

