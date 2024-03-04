package Zeeslag.Core.Dimension;

public abstract class Dimension {

    private int width;
    private int height;

    // Constructors

    public Dimension() {
        this.width = 1;
        this.height = 1;
    }

    public Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // Getters and Setters

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
