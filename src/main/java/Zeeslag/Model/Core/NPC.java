package Zeeslag.Model.Core;

import Zeeslag.Model.GameManager;
import Zeeslag.Model.helper.SceneUtil;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;

public class NPC extends Player {
    /**
     * Computer part of the seabattle game.
     */


    private List<Coord> attackedCoordinates; //A list with all the attack coord to prevent double hits.
    private List<Coord> hitShipCoords; //A List with the hitted coords to make a smart calculation
    private Coord attackCoord; // A variable to store the coord that is being attacked.


    public NPC(String name) {
        super(name);
        super.getBoard().placeRandomShips();
        this.attackedCoordinates = new ArrayList<>();
        this.hitShipCoords = new ArrayList<>();
    }



    /**
     * Checks for if the hit list is empty so that it can shoot randomly
     * @param otherPlayer
     * @return
     */
    public boolean attackPlayer(Player otherPlayer) {
        if(getHitShipCoords().isEmpty()) {
            return randomAttack(otherPlayer);
        } else {
            return searchForNeighbor(otherPlayer);
        }
    }


    /**
     * Calculation a random method.
     * This method will never attack a coord that already has been attacked
     * If it is a hit it will add the ship to the hitShip list.
     * @param otherPlayer
     * @return false if the attack has failed
     */
    private boolean randomAttack(Player otherPlayer) {
        Coord attackCoord;
        do {
            attackCoord = generateRandomAttackCoord();
        } while (getAttackedCoordinates().contains(attackCoord));

        if (attackCoord != null) {
            boolean hit = super.attack(otherPlayer, attackCoord.getX(), attackCoord.getY());
            if (hit) {
                Cell cell = getOpponentBoard().getCell(attackCoord.getX(), attackCoord.getY());
                if (cell.hasShip()) {
                    getHitShipCoords().add(attackCoord);
                }
                // Add the attacked coordinate to the list
                getAttackedCoordinates().add(attackCoord);
                return true;
            }
        }
        return false;
    }


    /**
     * Smart computer tactic
     * If the Hit list isn't empty it is going to search for a neighbor cell.
     * @param otherPlayer
     * @return null if the attackCoord has failed.
     */
    private boolean searchForNeighbor(Player otherPlayer) {
        attackCoord = generateRandomAttackCoord();

        Coord lastAttackCoord = (!getAttackedCoordinates().isEmpty()) ? getAttackedCoordinates().get(getAttackedCoordinates().size() - 1) : null;

        // Get the last hit ship coordinate
        Coord lastHitShipCoord = (!getHitShipCoords().isEmpty()) ? getHitShipCoords().get(0) : null;

        // If the last hit ship has been sunk, clear hit ship coordinates and return false
        if (lastHitShipCoord != null) {
            Cell lastHitCell = otherPlayer.getBoard().getCell(lastHitShipCoord.getX(), lastHitShipCoord.getY());
            if (lastHitCell != null && lastHitCell.hasShip() && lastHitCell.getShip().isSunk()) {
                getHitShipCoords().clear();
                return false;
            }
        }

        // Use the last attacked coordinate if available
        Coord lastCoord = (lastAttackCoord != null) ? lastAttackCoord : lastHitShipCoord;

        // If both lastAttackCoord and lastHitShipCoord are null, return false as there's no context to base the search on
        if (lastCoord == null) {
            return false;
        }

        // Define the deltas for neighboring cells
        int[] dx = {-1, 0, 1, 0}; // 1 is right, -1 is left
        int[] dy = {0, 1, 0, -1}; // 1 is down, -1 is up

        // Try to attack neighboring cells in a randomized order
        List<Integer> directions = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            directions.add(i);
        }
        Collections.shuffle(directions);

        // Check each neighboring cell
        for (int direction : directions) {
            int nx = lastCoord.getX() + dx[direction];
            int ny = lastCoord.getY() + dy[direction];

            // If the neighboring cell is within the board bounds and has not been attacked
            if (otherPlayer.getBoard().isValidPoint(nx, ny)) {
                Coord neighborCoord = new Coord(nx, ny);
                if (!getAttackedCoordinates().contains(neighborCoord)) {
                    // Attempt to attack the neighboring cell
                    boolean hit = super.attack(otherPlayer, nx, ny);
                    
                    // If the attack is successful, add the coordinates to hit ship coords and attacked coordinates
                    if (hit) {
                        getHitShipCoords().add(neighborCoord);
                        getAttackedCoordinates().add(neighborCoord);
                        attackCoord = neighborCoord; // Set the attack coordinate
                        return true; // Return true to indicate a successful hit
                    }
                }
            }
        }

        // If no valid neighboring cell is found, return false
        return false;
    }


    /**
     * Generating a random coord between the board sizes.
     * @return
     */
    // Method to generate a random attack coordinate
// Method to generate a random attack coordinate
    private Coord generateRandomAttackCoord() {
        int boardSize = getBoard().getSizeBoard();
        while (true) {
            int x = (int) (Math.random() * boardSize);
            int y = (int) (Math.random() * boardSize);
            Coord attackCoord = new Coord(x, y);
            this.setAttackCoord(attackCoord); // Update attackCoord immediately after generating the random coordinate
            if (!getAttackedCoordinates().contains(attackCoord)) {
                return attackCoord;
            } else {
                System.out.println("Already attacked: (" + x + ", " + y + ")");
            }
        }
    }





    public List<Coord> getAttackedCoordinates() {
        return attackedCoordinates;
    }

    public List<Coord> getHitShipCoords() {
        return hitShipCoords;
    }

    public Coord getAttackCoord() {
        return attackCoord;
    }

    public void setAttackCoord(Coord attackCoord) {
        this.attackCoord = attackCoord;
    }
}
