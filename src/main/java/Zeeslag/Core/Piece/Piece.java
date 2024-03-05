package Zeeslag.Core.Piece;

import Zeeslag.Core.Coord.Coord;
import Zeeslag.Core.Dimension.Dimension;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Piece extends Dimension {

    private Coord coord;
    private boolean selected;
    private boolean placed;


    public Piece() {
        super(1, 2);
        this.placed = false;
    }

    public Piece(int height) {
        super(1, height);
        this.placed = false;
    }

    public Piece(int height, int width) {
        super(1, height);
        this.placed = false;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void rotate() {
        if (this.isPlaced()) {
            return;
        }

        int temp = this.getWidth();
        this.setWidth(this.getHeight());
        this.setHeight(temp);
    }

    public int getRotation() {
        if (this.getHeight() > this.getWidth()) {
            return 0;
        }

        return 90;
    }

    public void lock() {
        this.setSelected(false);
        this.setPlaced(true);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "coord=" + coord +
                ", selected=" + selected +
                ", placed=" + placed +
                '}';
    }
}
