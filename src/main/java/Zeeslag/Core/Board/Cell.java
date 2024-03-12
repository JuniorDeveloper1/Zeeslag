package Zeeslag.Core.Board;

import Zeeslag.Core.Coord.Coord;
import Zeeslag.Core.Piece.Piece;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    public Coord coord;
    public Piece piece = null;
    public boolean isShot = false;

    private Board board;

    public Cell(int x, int y, Board board){
        super(30,30);
        this.coord = new Coord(x, y);
        this.board = board;
        setFill(Color.WHITE);
        setStroke(Color.BLACK);
    }

    public boolean shoot(){
        isShot = true;
        setFill(Color.BLACK);

        if(board)

    }
}