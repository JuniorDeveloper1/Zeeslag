package Zeeslag.Model.Core;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private final Cell[][] cells;
    private  List<Ship> placedShips;
    private boolean allShipsPlaced;

    private int sizeBoard = 10;

    public Board() {
        this.cells = new Cell[sizeBoard][sizeBoard];
        this.placedShips = new ArrayList<>();
        this.initializeShips();
        initializeCells();
    }

    public Board(int sizeBoard) {
        this.sizeBoard = sizeBoard;
        this.cells = new Cell[sizeBoard][sizeBoard];
        this.placedShips = new ArrayList<>();
        this.initializeShips();
        initializeCells();
    }

    /**
     * Clearing the ship list first
     * Initializing the ships
     */
    private void initializeShips(){
        getPlacedShips().clear();
        getPlacedShips().add(new Ship(5));
        getPlacedShips().add(new Ship(4));
        getPlacedShips().add(new Ship(3));
        getPlacedShips().add(new Ship(3));
        getPlacedShips().add(new Ship(2));

    }

    /**
     * Making a board x board with cells of a certain size.
     */
    private void initializeCells() {
        for (int x = 0; x < sizeBoard; x++) {
            for (int y = 0; y < sizeBoard; y++) {
                if(sizeBoard > 10){
                    int size = (int) (sizeBoard /0.5);
                    cells[x][y] = new Cell(x, y, size);
                }else {
                    cells[x][y] = new Cell(x, y);
                }

            }
        }
    }

    /**
     * Making a temp list otherwise we will get an error
     * Then choosing the coords of the ships with the Coord class.
     * Choosing if it is vertical or not
     *
     * This process is for the bots
     */
    public void placeRandomShips() {
        Random rand = new Random();
        List<Ship> tempShips = new ArrayList<>(getPlacedShips()); // Maak een kopie van de lijst
        for (Ship ship : tempShips) {
            boolean vertical = rand.nextBoolean();
            int x = rand.nextInt(getSizeBoard());
            int y = rand.nextInt(getSizeBoard());
            while (!canPlaceShip(ship, x, y)) {
                x = rand.nextInt(getSizeBoard());
                y = rand.nextInt(getSizeBoard());
                vertical = rand.nextBoolean();
                ship.setVertical(vertical);
            }
            placeShip(ship, x, y);
        }
    }


    /**
     *
     * Checking if the ship should be placed vertical or not.
     * If we can place the ship on the desired coord then it will return true
     * We will get the cell and place a ship there.
     * Ship gets added to the list
     * @param ship The ship
     * @param x x-coord
     * @param y y-coord
     * @return the preffered coord
     */
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


    /**
     * Making a check if the ship is between the X - Y coords of the board
     * @param ship user ship
     * @param x x-coord
     * @param y y-coord
     * @return Ship can be placed.
     */
    private boolean canPlaceShip(Ship ship, int x, int y) {
        int length = ship.getSize();

        if (ship.isVertical()) {
            if (y + length > getSizeBoard()) {
                return false;
            }

            for (int i = y; i < y + length; i++) {
                if (!isValidPoint(x, i) || getCell(x, i).hasShip()) {
                    return false;
                }
            }
        } else {
            if (x + length > getSizeBoard()) {
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

    /**
     * Checking if the ship is between the board sizes
     * @return passes the check
     */

    public boolean isValidPoint(int x, int y) {
        return x >= 0 && x < getSizeBoard() && y >= 0 && y < getSizeBoard();
    }

    /**
     * Getting the cell that is on that specific coord
     */
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

    public int getSizeBoard() {
        return sizeBoard;
    }
}