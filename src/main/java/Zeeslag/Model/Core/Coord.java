package Zeeslag.Model.Core;

import java.util.Objects;
import java.util.Random;

public class Coord {
    /**
     * Represents the Coord on a board.
     */


    private int x;
    private int y;


    public Coord() {
        this.x = 1;
        this.y = 1;
    }

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Methods

    public int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }

    public void setCoords(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return x == coord.x && y == coord.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }



    @Override
    public String toString() {
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    // Getters and Setters

}
