package Zeeslag.Model.Core;

public class Ship extends Coord {
    private int size;
    private int hits; // New field to keep track of hits
    private boolean vertical;
    private boolean isSunk;

    public Ship(int size) {
        this.size = size;
        this.hits = 0; // Initialize hits to 0
        setSunk(false);
    }

    public void hit() {
        hits++; // Increment hits when the ship is hit
        if (hits >= size) {
            setSunk(true); // Mark ship as sunk if all its cells are hit
        }
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

    public boolean isHit(int x, int y) {
        // Check if the ship is vertical or horizontal and if the position matches
        if (vertical) {
            return getX() == x && y >= getY() && y < getY() + size;
        } else {
            return getY() == y && x >= getX() && x < getX() + size;
        }
    }
}