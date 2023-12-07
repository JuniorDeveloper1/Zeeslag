package Zeeslag;

public class Game {

    public Game() {
        this.board1 = new Board(20, 20);
        this.board2 = new Board(20, 20);
    }

    private Board board1;
    private Board board2;

    public Board getBoard1() {
        return board1;
    }

    public void setBoard1(Board board1) {
        this.board1 = board1;
    }

    public Board getBoard2() {
        return board2;
    }

    public void setBoard2(Board board2) {
        this.board2 = board2;
    }

    // Methodes
    public void loadDefault() {
        this.getBoard1().placeAllRandomally();
        this.getBoard2().placeAllRandomally();
    }
}
