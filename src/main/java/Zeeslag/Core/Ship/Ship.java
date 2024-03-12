package Zeeslag.Core.Ship;

import Zeeslag.Core.Dimension.Dimension;

public class Ship extends Dimension {
    private int size;
    private boolean isPlaced;
    private int health;
    private boolean vertical;

    public Ship(int size) {
        this.size = size;
        this.health = size;
        this.isPlaced = false;
    }

    public void hit() {
        health--;
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
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
