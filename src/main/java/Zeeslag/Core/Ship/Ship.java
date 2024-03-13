package Zeeslag.Core.Ship;

import Zeeslag.Core.Dimension.Dimension;

public class Ship extends Dimension {
    private int size;
    private int health;
    private boolean vertical;

    public Ship(int size) {
        this.size = size;
        this.health = size;
    }

    public void hit() {
        health--;
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
}
