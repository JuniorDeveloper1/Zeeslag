package Zeeslag;

public class Piece {

    public Piece() {
        this.height = 3;
        this.width = 1;
        this.placed = false;
    }

    public Piece(int height) {
        this.height = height;
        this.width = 1;
        this.placed = false;
    }

    public Piece(int height, int width) {
        this.height = height;
        this.width = width;
        this.placed = false;
    }

    private int height;
    private int width;
    private int coordX;
    private int coordY;
    private boolean selected;
    private boolean placed;

    public int getHeight() {
        return height;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    private void setWidth(int width) {
        this.width = width;
    }

    public int getCoordX() {
        return coordX;
    }

    private void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    private void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public boolean isPlaced() {
        return placed;
    }

    private void setPlaced(boolean placed) {
        this.placed = placed;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    // Methodes
    public void rotate() {
        if (this.isPlaced()) {
            return;
        }

        int temp = this.getWidth();
        this.setWidth(this.getHeight());
        this.setHeight(temp);
    }

    public String getImage() {
        if (this.getHeight() > this.getWidth()) {
            return "images/piece-" + this.getHeight() + ".png";
        }

        return "images/piece-"+this.getWidth()+".png";
    }

    public int getImageRotation() {
        if (this.getHeight() > this.getWidth()) {
            return 0;
        }

        return 90;
    }

    public void setCoords(int coordX, int coordY) {
        if (this.isPlaced()) {
            return;
        }

        this.setCoordX(coordX);
        this.setCoordY(coordY);
    }

    public void lock() {
        this.setSelected(false);
        this.setPlaced(true);
    }

}
