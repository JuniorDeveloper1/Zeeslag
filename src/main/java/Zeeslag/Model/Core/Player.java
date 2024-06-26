package Zeeslag.Model.Core;

import Zeeslag.Model.GameManager;
import Zeeslag.Model.helper.SceneUtil;
import Zeeslag.View.Win.WinPresenter;
import Zeeslag.View.Win.WinView;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.UUID;

public  class Player {
    private GameManager gameManager = GameManager.getInstance();
    private String name;
    private  Board board, opponentBoard;
    private final UUID uuid;
    private int currentIndex = 0; //Calculating the index of the placed ships
    private int amountOfAttacks = 0; //Total attacks count

    private int amountLeaderBoardUpdates = 0; //A check if the leaderboard already has been updated.

    public Player(String name) {
        this.name = name;
        this.board = new Board();
        this.opponentBoard = new Board();
        this.uuid = generateUUID();
        this.amountOfAttacks = 0;
        this.amountLeaderBoardUpdates = 0;
    }

    /**
     * Attacking the player only if the player hasn't won yet.
     * Making the Cell color appear different if hit or didn't hit a ship.
     * @param otherplayer
     * @param x
     * @param y
     * @return
     */
    public boolean attack(Player otherplayer, int x, int y) {
            Cell cell = opponentBoard.getCell(x, y);
            Cell opponentCell = otherplayer.getBoard().getCell(x, y);
            this.setAmountOfAttacks(getAmountOfAttacks() + 1);
            if (opponentCell.hasShip()) {
                if(opponentCell.isHit() && cell.isHit()){
                    SceneUtil.showAlert("Already hit!", "You have already hit this cell!");
                    return false;
                }
                Ship ship = opponentCell.getShip();
                cell.setFill(Color.RED);
                opponentCell.setFill(Color.RED);
                ship.hit();

                cell.setHit(true);
                opponentCell.setHit(true);
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
        return true;
    }

    /**
     * A check if all the other player ships have been sunk
     *
     * Making a check if the leaderBoard already has been updated
     * If not the player can keep clicking and adding him self to the win file.
     * @param otherPlayer
     * @return
     */
    public boolean hasWon(Player otherPlayer, Scene view) {
        for (Ship ship : otherPlayer.getBoard().getPlacedShips()) {
            if (!ship.isSunk()) {
                return false;
            }
        }

        if(getAmountLeaderBoardUpdates() == 0){
            /**
             * We gebruiken de setAmountLeaderBoardUpdates
             * zodat de speler niet oneindig kan klikken zodat hij oneindig in de playergamedata.txt file komt.
             */
            PlayerGameData.save(this.getName(), this.getAmountOfAttacks());
            getGameManager().getLeaderboard().updateLeaderboardForPlayer(this.getName());
            this.setAmountLeaderBoardUpdates(1);

            openWinView(view);
        }
        return true;
    }

    private void openWinView(Scene view) {
        WinView winView = new WinView();
        WinPresenter winPresenter = new WinPresenter(winView);
        SceneUtil.closeScene(view);
        SceneUtil.openView(winPresenter);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * Making sure that the current index cannot be higher then 5
     * 5 = totalShip that can be placed.
     * @param currentIndex
     */
    public void setCurrentIndex(int currentIndex) {
        // Check if currentIndex is within bounds
        if (currentIndex < 0) {
            currentIndex = 0;
        }

        if (currentIndex >= board.getAmountShips()) {
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
