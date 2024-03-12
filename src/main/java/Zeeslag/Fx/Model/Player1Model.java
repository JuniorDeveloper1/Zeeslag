package Zeeslag.Fx.Model;

import Zeeslag.Core.Board.Board;
import Zeeslag.Core.Game.GameManager;
import Zeeslag.Core.Ship.Ship;

import java.util.ArrayList;
import java.util.List;

public class Player1Model {

    public GameManager gameManager = GameManager.getInstance();
    public Board playerBoard = gameManager.getPlayer1().getBoard();
    private int currentIndex = 0;

    public Player1Model() {
        for (int i = 0; i < playerBoard.getPlacedShips().size(); i++) {
            Ship ship = playerBoard.getPlacedShips().get(i);
            System.out.println( "i :"+ i +" size: " + ship.getSize());
        }


    }




    public boolean placeShip(int x, int y) {
        final int totalShips = playerBoard.getPlacedShips().size();
        final int maxShips = 5;

        if (currentIndex >= totalShips || currentIndex >= maxShips) {
            if(!(maxShips == 5)) {this.setCurrentIndex(currentIndex - 1);}
            return false;
        }

        Ship currentShip = playerBoard.getPlacedShips().get(currentIndex);
        int size = currentShip.getSize();


        if (x + size > 10) {
            if(!(maxShips == 5)) {this.setCurrentIndex(currentIndex - 1);}
            return false;
        }


        if (y + size > 10) {
            if(!(maxShips == 5)) {this.setCurrentIndex(currentIndex - 1);}
            return false;
        }

        for (int j = 0; j < size; j++) {
            if (playerBoard.getCell(x + j, y).hasShip()) {
                if(!(maxShips == 5)) {this.setCurrentIndex(currentIndex - 1);}
                return false;
            }
        }

        for (int j = 0; j < size; j++) {
            playerBoard.placeShip(new Ship(1), x + j, y);
        }

        setCurrentIndex(getCurrentIndex() + 1);
        System.out.println("Index : " + getCurrentIndex());
        return true;
    }


    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        if(currentIndex < 0) {
            currentIndex = 0;
        }
        this.currentIndex = currentIndex;
    }

    public Board getBoard() {
        return playerBoard;
    }
}
