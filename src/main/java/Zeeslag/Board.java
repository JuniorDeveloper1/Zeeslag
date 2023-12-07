package Zeeslag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board implements Dimension {

    public Board() {
        this.width = 20;
        this.height = 20;
        this.pieces = new ArrayList<>();
        this.generateDefaultPieces();
    }

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.pieces = new ArrayList<>();
        this.generateDefaultPieces();
    }

    public Board(int width, int height, List<Piece> pieces) {
        this.width = width;
        this.height = height;
        this.pieces = pieces;
    }

    private int width;
    private int height;
    private List<Piece> pieces;

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }


    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    // Methodes
    public void generateDefaultPieces() {
        this.getPieces().add(new Piece(getPieces().get(0).getCoordsX()
                ,getPieces().get(0).getCoordsY(),
                4));
        this.getPieces().add(new Piece(getPieces().get(1).getCoordsX()
                ,getPieces().get(1).getCoordsY(),
                3));
        this.getPieces().add(new Piece(getPieces().get(2).getCoordsX()
                ,getPieces().get(2).getCoordsY(),
                3));
        this.getPieces().add(new Piece(getPieces().get(3).getCoordsX()
                ,getPieces().get(3).getCoordsY(),
                2));
        this.getPieces().add(new Piece(getPieces().get(4).getCoordsX()
                ,getPieces().get(4).getCoordsY(),
                2));
        this.getPieces().add(new Piece(getPieces().get(5).getCoordsX()
                ,getPieces().get(5).getCoordsY(),
                2));
    }

    private boolean isCoordOnBoard(int coordX, int coordY) {
        if (coordX >= 1 && coordX <= this.getHeight() && coordY >= 1 && coordY <= this.getWidth()) {
            return true;
        }
        return false;
    }

    private boolean isCoordFree(int coordX, int coordY) {
        for (int i = 0; i < this.getPieces().size(); i++) {
            if (this.getPieces().get(i).isPlaced()) {
                if (this.getPieces().get(i).getCoordsX() == coordX && this.getPieces().get(i).getCoordsY() == coordY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean canPieceBePlaced(Piece piece) {
        for (int i = 0; i < piece.getWidth(); i++) {
            for (int j = 0; i < piece.getHeight(); j++) {
                int coordY = piece.getCoordsX() + i;
                int coordX = piece.getCoordsY() + j;
                if (!this.isCoordFree(coordX, coordY) || !this.isCoordOnBoard(coordX, coordY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void placePieceRandomally(Piece piece) {
        Random random = new Random();

        int randomX = random.nextInt(this.getHeight()) + 1;
        int randomY = random.nextInt(this.getWidth()) + 1;
        piece.setCoords(randomX, randomY);

        boolean rotate = random.nextBoolean();
        if (rotate) {
            piece.rotate();
        }

        while (!this.canPieceBePlaced(piece)) {
            randomX = random.nextInt(this.getHeight()) + 1;
            randomY = random.nextInt(this.getWidth()) + 1;
            piece.setCoords(randomX, randomY);

            rotate = random.nextBoolean();
            if (rotate) {
                piece.rotate();
            }
        }
    }

    public void placeAllRandomally() {
        for (int i = 0; i < this.getPieces().size(); i++) {
            this.placePieceRandomally(this.getPieces().get(i));
        }
    }

    public void recieveAttack(int coordsX, int coordY) {
        for (int i = 0; i < this.getPieces().size(); i++) {
            Piece piece = this.getPieces().get(i);
            if(piece.getCoordsX() == coordsX
                    && piece.getCoordsY() == coordY)
            {
                //TODO: If it hits then remove tile? ADD LOGIC
            }
        }
    }


}
