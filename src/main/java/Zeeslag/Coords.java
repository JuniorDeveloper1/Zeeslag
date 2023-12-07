package Zeeslag;

public abstract class Coords {
    private int coordsX;
    private int coordsY;

    public Coords(int coordsX, int coordsY){
        this.coordsX = coordsX;
        this.coordsY = coordsY;
    }

    public int getCoordsX() {
        return coordsX;
    }

    public void setCoordsX(int coordsX) {
        this.coordsX = coordsX;
    }

    public int getCoordsY() {
        return coordsY;
    }

    public void setCoordsY(int coordsY) {
        this.coordsY = coordsY;
    }

    public void setCoords(int coordsX, int coordsY){
            setCoordsX(coordsX);
            setCoordsY(coordsY);
    }
}
