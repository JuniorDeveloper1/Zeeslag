package Zeeslag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board extends Dimension {
    private List<Piece> pieces;
    private List<Bomb> bombs;


    public Board() {
        super(10, 10);
        this.pieces = new ArrayList<>();
        this.generateDefaultPieces();
    }

    public Board(int width, int height) {
        super(width, height);
        this.pieces = new ArrayList<>();
        this.generateDefaultPieces();
    }

    public Board(int width, int height, List<Piece> pieces) {
        super(width, height);
        this.pieces = pieces;
    }


    public void generateDefaultPieces() {
        this.getPieces().add(new Piece(4));
        this.getPieces().add(new Piece(3));
        this.getPieces().add(new Piece(3));
        this.getPieces().add(new Piece(2));
        this.getPieces().add(new Piece(2));
        this.getPieces().add(new Piece(2));
    }

    private boolean isCoordOnBoard(int coordX, int coordY) {
        return coordX >= 1 && coordX <= this.getHeight() && coordY >= 1 && coordY <= this.getWidth();
    }

    private boolean isCoordFree(int coordX, int coordY) {
        for (int i = 0; i < this.getPieces().size(); i++) {
            if (this.getPieces().get(i).isPlaced()) {
                if (this.getPieces().get(i).getCoord().getX() == coordX && this.getPieces().get(i).getCoord().getY() == coordY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean canPieceBePlaced(Piece piece) {
        for (int i = 0; i < piece.getWidth(); i++) {
            for (int j = 0; i < piece.getHeight(); j++) {
                int coordY = piece.getCoord().getY() + i;
                int coordX = piece.getCoord().getX() + j;
                if (!this.isCoordFree(coordX, coordY) || !this.isCoordOnBoard(coordX, coordY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void placePieceRandomly(Piece piece) {
        Random random = new Random();

        int randomX = random.nextInt(this.getHeight()) + 1;
        int randomY = random.nextInt(this.getWidth()) + 1;
        piece.getCoord().setCoords(randomX, randomY);

        boolean rotate = random.nextBoolean();
        if (rotate) {
            piece.rotate();
        }

        while (!this.canPieceBePlaced(piece)) {
            randomX = random.nextInt(this.getHeight()) + 1;
            randomY = random.nextInt(this.getWidth()) + 1;
            piece.getCoord().setCoords(randomX, randomY);

            rotate = random.nextBoolean();
            if (rotate) {
                piece.rotate();
            }
        }

        piece.lock();
    }

    public void placeAllRandomly() {
        for (int i = 0; i < this.getPieces().size(); i++) {
            this.placePieceRandomly(this.getPieces().get(i));
        }
    }

    // Getters and Setters
    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public void setBombs(List<Bomb> bombs) {
        this.bombs = bombs;
    }

}
