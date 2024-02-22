package Zeeslag.modulesVerzinBetereNaamXd.Player;

import Zeeslag.modulesVerzinBetereNaamXd.Board.Board;
import Zeeslag.modulesVerzinBetereNaamXd.Bomb.Bomb;
import Zeeslag.modulesVerzinBetereNaamXd.Leaderboard.Leaderboard;
import Zeeslag.modulesVerzinBetereNaamXd.Piece.Piece;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class Player {
    private String name;
    private Board board;

    private final UUID uuid;

    public Player(String name, Board board){
        this.name = name;
        this.board = board;
        this.uuid = generateUUID();
    }

    public void attack(Player otherPlayer, int x, int y) throws IOException {
        if(otherPlayer.equals(this)){
            throw new IllegalArgumentException("Exception: you can only attack a different player");
        }

        Bomb bomb = new Bomb(x, y);
        if (otherPlayer.getBoard().recieveBomb(bomb)) {
            System.out.println("Je hebt geraakt");
        } else {
            System.out.println("Je hebt gemist");
        }

        if(hasWon(otherPlayer)){
            //TODO: Stop fighting
            Leaderboard leaderboard = new Leaderboard();
            leaderboard.incrementWins(this);
            /**
             * Leaderboard leaderboard... moet je laten.
             * We laden al de player data hiermee.
             * Om te zien als de speler al gewonnen heeft.
             */

            //TODO: Add open win screen. Or something else.

        }

    }

    private boolean hasWon(Player otherPlayer) {
        List<Piece> opponentPieces = otherPlayer.getBoard().getPieces();

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
    }

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

