package Zeeslag.Model.Core;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    /**
     * This presents a cell on the board class.
     * With attributes to check if the cell has a ship or has been hit.
     */
    private final Coord coord;
    private boolean isHit;
    private Ship ship;


    public Cell(int x, int y) {
        super(30, 30);
        this.coord = new Coord(x, y);
        this.isHit = false;
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
    }

    public Cell(int x, int y, int size) {
        super(size, size);
        this.coord = new Coord(x, y);
        this.isHit = false;
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public boolean hasShip() {
        return ship != null;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Coord getCoord() {
        return coord;
    }

}
