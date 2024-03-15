package Zeeslag.Model.Core;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private final Cell[][] cells;
    private  List<Ship> placedShips;
    private boolean allShipsPlaced;

    public Board() {
        this.cells = new Cell[10][10];
        this.placedShips = new ArrayList<>();
        this.initializeShips();
        initializeCells();
    }

    private void initializeShips(){
        getPlacedShips().clear();
        getPlacedShips().add(new Ship(5));
        getPlacedShips().add(new Ship(4));
        getPlacedShips().add(new Ship(3));
        getPlacedShips().add(new Ship(3));
        getPlacedShips().add(new Ship(2));

    }

    private void initializeCells() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
    }

    // Plaats de schepen willekeurig op het bord
    public void placeRandomShips() {
        Random rand = new Random();
        List<Ship> tempShips = new ArrayList<>(getPlacedShips()); // Maak een kopie van de lijst
        for (Ship ship : tempShips) {
            boolean vertical = rand.nextBoolean();
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            while (!canPlaceShip(ship, x, y)) {
                x = rand.nextInt(10);
                y = rand.nextInt(10);
                vertical = rand.nextBoolean();
                ship.setVertical(vertical);
            }
            placeShip(ship, x, y);
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

    public boolean isValidPoint(int x, int y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

    public Cell getCell(int x, int y) {
        if (isValidPoint(x, y)) {
            return cells[x][y];
        } else {
            return null;
        }
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

    public boolean isAllShipsPlaced() {
        return allShipsPlaced;
    }

    public void setAllShipsPlaced(boolean allShipsPlaced) {
        this.allShipsPlaced = allShipsPlaced;
    }
}