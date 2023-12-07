package Zeeslag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

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

    public int getWidth() {
        return width;
    }

    private void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    private void setHeight(int height) {
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
        this.getPieces().add(new Piece(4));
        this.getPieces().add(new Piece(3));
        this.getPieces().add(new Piece(3));
        this.getPieces().add(new Piece(2));
        this.getPieces().add(new Piece(2));
        this.getPieces().add(new Piece(2));
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
                if (this.getPieces().get(i).getCoordX() == coordX && this.getPieces().get(i).getCoordY() == coordY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean canPieceBePlaced(Piece piece) {
        for (int i = 0; i < piece.getWidth(); i++) {
            for (int j = 0; i < piece.getHeight(); j++) {
                int coordY = piece.getCoordY() + i;
                int coordX = piece.getCoordX() + j;
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

}
