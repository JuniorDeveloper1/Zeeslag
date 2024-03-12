package Zeeslag.Core.Player;

import Zeeslag.Core.Board.Board;
import Zeeslag.Core.Bomb.Bomb;
import Zeeslag.Core.Leaderboard.Leaderboard;
import Zeeslag.Core.Ship.Ship;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class Player {
    private final String name;
    private final Board board;

    private final UUID uuid;

    public Player(String name){
        this.name = name;
        this.board = new Board();
        this.uuid = generateUUID();
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

    public UUID getUuid() {
        return uuid;
    }

    private UUID generateUUID() {
        return UUID.randomUUID();
    }
}

