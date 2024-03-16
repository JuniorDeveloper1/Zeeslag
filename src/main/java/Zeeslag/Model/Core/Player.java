package Zeeslag.Model.Core;

import Zeeslag.Model.GameManager;
import Zeeslag.Model.helper.SceneUtil;
import javafx.scene.paint.Color;

import java.util.UUID;

public  class Player {
    private GameManager gameManager = GameManager.getInstance();
    private String name;
    private  Board board, opponentBoard;
    private final UUID uuid;
    private int currentIndex = 0;
    private int amountOfAttacks = 0;

    private int amountLeaderBoardUpdates = 0;

    public Player(String name) {
        this.name = name;
        this.board = new Board();
        this.opponentBoard = new Board();
        this.uuid = generateUUID();
        this.amountOfAttacks = 0;
        this.amountLeaderBoardUpdates = 0;
    }

    public boolean attack(Player otherplayer, int x, int y) {
        if (!hasWon(otherplayer)) {
            Cell cell = opponentBoard.getCell(x, y);
            Cell opponentCell = otherplayer.getBoard().getCell(x, y);
            this.setAmountOfAttacks(getAmountOfAttacks() + 1);
            if (opponentCell.hasShip()) {
                Ship ship = opponentCell.getShip();
                cell.setFill(Color.RED);
                opponentCell.setFill(Color.RED);
                ship.hit();
                if (ship.isSunk()) {
                    SceneUtil.showAlert(otherplayer.getName() + " schip is gezonken!",
                            "Schip met grootte " + ship.getSize() + " is gezonken!!");
                }
                getGameManager().getTurn().setPlayerTurn(this);
            } else {
                cell.setFill(Color.BLACK);
                opponentCell.setFill(Color.BLACK);
                getGameManager().getTurn().setPlayerTurn(otherplayer);
            }
        } else {
            SceneUtil.showAlert(getName() + "WON!", getName() + " Heeft het spel gewonnen!");
        }
        return true;
    }

    public boolean hasWon(Player otherPlayer) {
        for (Ship ship : otherPlayer.getBoard().getPlacedShips()) {
            if (!ship.isSunk()) {
                return false;
            }
        }

        if(getAmountLeaderBoardUpdates() == 0){
            PlayerGameData.save(this.getName(), this.getAmountOfAttacks());
            getGameManager().getLeaderboard().updateLeaderboard(this.getName());
            this.setAmountLeaderBoardUpdates(1);
            /**
             * We gebruiken de setAmountLeaderBoardUpdates
             * zodat de speler niet oneindig kan klikken zodat hij oneindig in de playergamedata.txt file komt.
             */
        }
        return true;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        if (currentIndex < 0) {
            currentIndex = 0;
        }

        if (currentIndex >= 5) {
            getBoard().setAllShipsPlaced(true);
        }
        this.currentIndex = currentIndex;
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public Board getOpponentBoard() {
        return opponentBoard;
    }

    public UUID getUuid() {
        return uuid;
    }

    private UUID generateUUID() {
        return UUID.randomUUID();
    }

    public int getAmountOfAttacks() {
        return amountOfAttacks;
    }


    public void setAmountOfAttacks(int amountOfAttacks) {
        this.amountOfAttacks = amountOfAttacks;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setOpponentBoard(Board opponentBoard) {
        this.opponentBoard = opponentBoard;
    }

    public int getAmountLeaderBoardUpdates() {
        return amountLeaderBoardUpdates;
    }

    public void setAmountLeaderBoardUpdates(int amountLeaderBoardUpdates) {
        this.amountLeaderBoardUpdates = amountLeaderBoardUpdates;
    }
}
