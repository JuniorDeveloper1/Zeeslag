package Zeeslag.Core.Board;

import Zeeslag.Core.Ship.Ship;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final Cell[][] cells;
    private  List<Ship> placedShips;

    public Board() {
        this.cells = new Cell[10][10]; // Assuming a 10x10 grid
        this.placedShips = new ArrayList<>();
        this.initializeShips();
        initializeCells();
    }

    private void initializeShips(){
        getPlacedShips().clear();
        getPlacedShips().add(new Ship(4));
        getPlacedShips().add(new Ship(3));
        getPlacedShips().add(new Ship(3));
        getPlacedShips().add(new Ship(2));
        getPlacedShips().add(new Ship(2));

    }

    private void initializeCells() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
    }


    public boolean placeShip(Ship ship, int x, int y) {
        if (canPlaceShip(ship, x, y)) {
            int length = ship.getSize();

            if (ship.isVertical()) {
                for (int i = y; i < y + length; i++) {
                    Cell cell = getCell(x, i);
                    cell.setShip(ship);
                    cell.setFill(Color.WHITE);
                    cell.setStroke(Color.GREEN);
                }
            } else {
                for (int i = x; i < x + length; i++) {
                    Cell cell = getCell(i, y);
                    cell.setShip(ship);
                    cell.setFill(Color.WHITE);
                    cell.setStroke(Color.GREEN);
                }
            }

            placedShips.add(ship);
            return true;
        }

        return false;
    }

    private boolean canPlaceShip(Ship ship, int x, int y) {
        int length = ship.getSize();

        if (ship.isVertical()) {
            if (y + length > 10) {
                return false;
            }

            for (int i = y; i < y + length; i++) {
                if (!isValidPoint(x, i) || getCell(x, i).hasShip()) {
                    return false;
                }
            }
        } else {
            if (x + length > 10) {
                return false;
            }

            for (int i = x; i < x + length; i++) {
                if (!isValidPoint(i, y) || getCell(i, y).hasShip()) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValidPoint(int x, int y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

    public Cell getCell(int x, int y) {
        if (isValidPoint(x, y)) {
            return cells[x][y];
        } else {
            return null;
        }
    }

    private List<Ship> createPieces() {
        List<Ship> pieces = new ArrayList<>();
        pieces.add(new Ship(4));
        pieces.add(new Ship(3));
        pieces.add(new Ship(3));
        pieces.add(new Ship(2));
        pieces.add(new Ship(2));
        return pieces;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public List<Ship> getPlacedShips() {
        return placedShips;
    }

    public void setPlacedShips(List<Ship> placedShips) {
        this.placedShips = placedShips;
    }
}
