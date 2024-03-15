package Zeeslag.Model.Core;

import Zeeslag.Model.GameManager;

import java.util.*;

public class NPC extends Player {

    private boolean isIntelligent = false;
    private List<Coord> attackedCoordinates;
    private List<Coord> hitShipCoords;
    private Coord attackCoord; // Private variable to store the attack coordinate


    public NPC(String name) {
        super(name);
        super.getBoard().placeRandomShips();
        this.attackedCoordinates = new ArrayList<>();
        this.hitShipCoords = new ArrayList<>();
        attackedCoordinates.clear();
        hitShipCoords.clear();

    }


    public boolean attackPlayer(Player otherPlayer) {
        System.out.println("SIZE HITSHIPS: " + getHitShipCoords().size());
        if (getHitShipCoords().isEmpty()) {
            System.out.println("RANDOM ATTACK");
            return randomAttack(otherPlayer);
        } else {
            System.out.println("INTELLIGENT ATTACK");
            return searchForNeighbor(otherPlayer);
        }
    }


    // Random attack method
    private boolean randomAttack(Player otherPlayer) {
        attackCoord = generateRandomAttackCoord();
        if (getAttackedCoordinates() != null && !getAttackedCoordinates().contains(getAttackCoord())) {
            boolean hit = super.attack(otherPlayer, getAttackCoord().getX(), getAttackCoord().getY());
            if (hit) {
                Cell cell = getOpponentBoard().getCell(getAttackCoord().getX(), getAttackCoord().getY());
                if(cell.hasShip()){
                    getHitShipCoords().add(getAttackCoord());
                }

                return true;
            }
            getAttackedCoordinates().add(getAttackCoord());
        }
        return false;
    }

    private boolean searchForNeighbor(Player otherPlayer) {
        attackCoord = null; // Reset attackCoord
        // Get the last attacked coordinate
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
                    System.out.println("ATTACKED: (" + nx + ", " + ny + ")");

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


    // Method to generate a random attack coordinate
    private Coord generateRandomAttackCoord() {
        while (true) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            Coord attackCoord = new Coord(x, y);
            if (!getAttackedCoordinates().contains(attackCoord)) {
                return attackCoord;
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
}