package Zeeslag.Model.Core;

public class Ship extends Coord {
    /**
     * Represents a ship in the game. It extends a coord.
     * Provides methods to handle hits, check if a ship is hit, and manage its state.
     */
    private int size;
    private int hits; // New field to keep track of hits
    private boolean vertical;
    private boolean isSunk;

    public Ship(int size) {
        super();
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

}
