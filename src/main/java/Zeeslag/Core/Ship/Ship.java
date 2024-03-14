package Zeeslag.Core.Ship;

import Zeeslag.Core.Dimension.Dimension;

public class Ship extends Dimension {
    private int size;
    private int health;
    private boolean vertical;

    private boolean isSunk;

    public Ship(int size) {
        this.size = size;
        this.health = size;
    }

    public void hit() {
        setSunk(true);
    }


    public boolean isAlive() {
        return health > 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public boolean isSunk() {
        return isSunk;
    }

    public void setSunk(boolean sunk) {
        isSunk = sunk;
    }
}
