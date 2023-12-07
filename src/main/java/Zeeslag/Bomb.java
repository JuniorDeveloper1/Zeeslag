public class Bomb {

    public Bomb(int coordX, int coordY) {
        this.coord = new Coord(coordX, coordY);
        this.hit = false;
    }

    public Bomb(Coord coord) {
        this.coord = coord;
        this.hit = false;
    }

    public Bomb(Coord coord, boolean hit) {
        this.coord = coord;
        this.hit = hit;
    }

    private Coord coord;
    private boolean hit;

    public Coord getCoord() {
        return coord;
    }

    private void setCoord(Coord coord) {
        this.coord = coord;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

}
