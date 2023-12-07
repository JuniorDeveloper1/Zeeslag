package Zeeslag;

public class Coord {


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

    // Getters and Setters

}