package Zeeslag.Fx.Model;

import Zeeslag.Core.Board.Board;
import Zeeslag.Core.Game.GameManager;
import Zeeslag.Core.Ship.Ship;

public class Player2Model {

    public GameManager gameManager = GameManager.getInstance();
    public Board playerBoard = gameManager.getPlayer2().getBoard();
    private int currentIndex = 0;

    public Player2Model() {
        for (int i = 0; i < playerBoard.getPlacedShips().size(); i++) {
            Ship ship = playerBoard.getPlacedShips().get(i);
            System.out.println( "i :"+ i +" size: " + ship.getSize());
        }
        gameManager.getPlayer2().getBoard().setAllShipsPlaced(false);
    }

    public boolean placeHorizontalShip(int x, int y) {
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

        for (int j = 0; j < size; j++) {
            if (playerBoard.getCell(x + j, y).hasShip()) {
                if(!(maxShips == 5)) {this.setCurrentIndex(currentIndex - 1);}
                return false;
            }
        }

        Ship ship = playerBoard.getPlacedShips().get(currentIndex);

        for (int j = 0; j < size; j++) {
            playerBoard.placeShip(ship, x + j, y);
            /**
             * The size 1 is for the auto completion.
             */
        }


        setCurrentIndex(getCurrentIndex() + 1);
        System.out.println("Index : " + getCurrentIndex());
        return true;
    }

    public boolean placeVerticalShip(int x, int y) {
        final int totalShips = playerBoard.getPlacedShips().size();
        final int maxShips = 5;

        Ship currentShip = playerBoard.getPlacedShips().get(currentIndex);
        int size = currentShip.getSize();

        currentShip.setVertical(true);

        if (currentIndex >= totalShips || currentIndex >= maxShips) {
            if(!(maxShips == 5)) {this.setCurrentIndex(currentIndex - 1);}
            return false;
        }


        if (y + size > 10) {
            if(!(maxShips == 5)) {this.setCurrentIndex(currentIndex - 1);}
            return false;
        }

        for (int j = 0; j < size; j++) {
            if (playerBoard.getCell(x, y+j).hasShip()) {
                if(!(maxShips == 5)) {this.setCurrentIndex(currentIndex - 1);}
                return false;
            }
        }
        Ship ship = playerBoard.getPlacedShips().get(currentIndex);

        for (int j = 0; j < size; j++) {
            playerBoard.placeShip(ship, x, y+j);
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

        if(currentIndex >= 5) {
            gameManager.getPlayer2().getBoard().setAllShipsPlaced(true);
        }
        this.currentIndex = currentIndex;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public Board getBoard() {
        return playerBoard;
    }
}
