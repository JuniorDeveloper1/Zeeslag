package Zeeslag;

import java.util.List;

public class Player {
    private String name;
    private Board board;

    public Player(String name, Board board){
        this.name = name;
        this.board = board;
    }

    public void attack(Player otherPlayer, int x, int y){
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
            System.out.println(this.getName() + " Has won!");
        }

    }

    public boolean hasWon(Player otherPlayer) {
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
}
