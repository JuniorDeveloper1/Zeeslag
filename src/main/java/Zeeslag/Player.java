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
        Bomb bomb = new Bomb(x, y);
        Coord bombC = bomb.getCoord();
        otherPlayer.getBoard().recieveBomb(bomb, bombC.getX(), bombC.getY());

        if(otherPlayer.equals(this)){
            System.out.println(" You can only attack you oponnent");
            return;
        }

        if(hasWon(otherPlayer)){
            //TODO: Stop fighting
        }

    }

    public boolean hasWon(Player otherPlayer) {
        List<Piece> opponentPieces = otherPlayer.getBoard().getPieces();
        List<Bomb> bombs = this.getBoard().getBombs();

        if((opponentPieces.size() != bombs.size())) {
            return false;
        }

        System.out.println(this.getName() + " Has won!");
        return true;
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }
}
