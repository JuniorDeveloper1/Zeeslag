package Zeeslag;

public class Player {
    private String name;
    private Board board;

    public Player(String name, Board board){
        this.name = name;
        this.board = board;
    }

    public void fireRocket(Player target, int coordsX, int coordsY){
        target.getBoard().recieveAttack(coordsX,coordsY);
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isWinner(boolean isWinner){
        return isWinner;
    }

}
