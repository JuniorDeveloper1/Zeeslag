package Zeeslag.Model;

import Zeeslag.Model.Core.*;
import Zeeslag.Model.helper.SceneUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;

public class GameManager {
    /**
     * GameManger is a singleton class
     * GameManager manages the game state, including players, boards, turns, and leaderboard.
     * It is a singleton class, so it doesn't risk any overriding or risking memory loss.
     * This class acts as a central hub for game data, preventing data loss
     */
    private Player player1, player2, bot;
    private boolean hasStarted = false;
    private Turn turn;
    private Leaderboard leaderboard;

    private boolean isPlayingAgainstBot = false;

    private static GameManager gameManager;

    private GameManager() {}

    public static GameManager getInstance() {
        if (gameManager == null) {
            gameManager = new GameManager();
        }
        return gameManager;
    }


    /**
     * Starts a new game with the specified player names and board size.
     * @param player1Name Name of the first player
     * @param player2Name Name of the second player (null for bot)
     * @param comboBox ComboBox containing board size options
     * @throws FileNotFoundException if leaderboard data file is not found
     */
    public void startGame(String player1Name, String player2Name, ComboBox comboBox) throws FileNotFoundException {
        Board player1Board = new Board((Integer) comboBox.getValue());
        Board player1OppBoard = new Board((Integer) comboBox.getValue());

        Board player2Board = new Board((Integer) comboBox.getValue());
        Board player2OppBoard = new Board((Integer) comboBox.getValue());



        player1 = new Player(player1Name);
        turn = new Turn(player1);
        leaderboard = new Leaderboard();
        setHasStarted(false);
        if(player2Name == null || player2Name.equals("") || player2Name.equals(" ")){
            bot = new NPC("bot");
            gameManager.setPlayer2(bot);
            setPlayingAgainstBot(true);
        }else {
            player2 = new Player(player2Name);
            setPlayingAgainstBot(false);
        }

        player1.setBoard(player1Board);
        player1.setOpponentBoard(player1OppBoard);

        player2.setBoard(player2Board);
        player2.setOpponentBoard(player2OppBoard);


    }

    /**
     * Plays the game with the provided player names and board size.
     * @param player1 Name of the first player
     * @param player2 Name of the second player (null for bot)
     * @param boardSize ComboBox containing board size options
     * @throws FileNotFoundException if leaderboard data file is not found
     */
    public void play(String player1, String player2, ComboBox boardSize) throws FileNotFoundException {

        /**
         * Maak het zo als je op play klikt dat er een button onder is dat erop staat:
         * add Player:
         */

        createHero(player1, player2);
        gameManager.startGame(player1, player2, boardSize);
        System.out.println(gameManager.getPlayer1().getName());
    }

    public void createHero(String sPlayer1, String sPlayer2) {
        if(sPlayer1 == null) {
            sPlayer1 = "ERROR";
        }

        if(sPlayer2 == null) {
            sPlayer2 = "Bot";
        }
        Player player1 = new Player(sPlayer1);
        Player player2 = new Player(sPlayer2);
        gameManager.setPlayer1(player1);
        gameManager.setPlayer2(player2);

    }

    /**
     * A check if the text field has the right regex.
     * @param textField Player 1 name
     * @param textField2 Player 2 name
     * @return true if it is correct
     */
    public boolean checkIfTextFieldIsCorrect(TextField textField, TextField textField2){
        int minLength = 3;
        int maxLength = 15;




        if(textField.getText().isEmpty()) {
            SceneUtil.showAlert("Wrong syntax","Je moet een naam invullen!");
            return false;
        }

        if(textField.getText().equals(textField2.getText())) {
            SceneUtil.showAlert("Wrong syntax","Namen mogen niet gelijk zijn!");
            return false;
        }

        if(textField2.getText().isEmpty()){
            /**
             * If it return true it means the player wants to play against the bot
             */
            return true;
        }

        if(textField.getLength() < minLength || textField2.getLength() <  minLength ||
                textField.getLength() > maxLength || textField2.getLength() > maxLength) {
            SceneUtil.showAlert("Wrong syntax","Names must be between 3 and 15 characters long.");
            return false;
        }
        return true;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public boolean isPlayerReady() {
        return player1.getBoard().isAllShipsPlaced();
    }

    public boolean hasStarted() {
        return hasStarted;
    }

    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

    public Turn getTurn() {
        return turn;
    }

    public Player getBot() {
        return bot;
    }
    public boolean bothPlayersReady() {
        return player1.getBoard().isAllShipsPlaced() && player2.getBoard().isAllShipsPlaced();
    }

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    public boolean isPlayingAgainstBot() {
        return isPlayingAgainstBot;
    }

    public void setPlayingAgainstBot(boolean playingAgainstBot) {
        isPlayingAgainstBot = playingAgainstBot;
    }
}
