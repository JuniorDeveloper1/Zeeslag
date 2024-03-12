package Zeeslag.Fx.Model;

import Zeeslag.Core.Board.Board;
import Zeeslag.Core.Game.GameManager;
import Zeeslag.Core.Ship.Ship;

import java.util.ArrayList;
import java.util.List;

public class Player1Model {

    public GameManager gameManager = GameManager.getInstance();
    public Board playerBoard = gameManager.getPlayer1().getBoard();

    public Player1Model() {
        for(Ship ship : playerBoard.getPlacedShips()) {
            System.out.println("SHIP SIZE " + ship.getSize());
        }
    }



    public boolean placeShip(int x, int y) {
        if (playerBoard != null) {
            return playerBoard.placeShip(new Ship(1), x, y); // Create a new Ship object and place it
        }
        return false;
    }


    public Board getBoard() {
        return playerBoard;
    }
}
