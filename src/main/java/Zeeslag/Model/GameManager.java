package Zeeslag.Model;

import Zeeslag.Model.Core.Leaderboard;
import Zeeslag.Model.Core.Player;
import Zeeslag.Model.Core.Turn;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;

public class GameManager {
    private Player player1, player2;
    private boolean hasStarted = false;
    private Turn turn;
    private Leaderboard leaderboard;

    private static GameManager gameManager;

    private GameManager() {}

    public static GameManager getInstance() {
        if (gameManager == null) {
            gameManager = new GameManager();
        }
        return gameManager;
    }

    public void startGame(String player1Name, String player2Name) throws FileNotFoundException {
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
        turn = new Turn(player1);
        leaderboard = new Leaderboard();
        setHasStarted(false);
    }

    public void play(String player1, String player2) throws FileNotFoundException {
        createHero(player1, player2);
        gameManager.startGame(player1, player2);
        System.out.println(gameManager.getPlayer1().getName());
    }

    public void createHero(String sPlayer1, String sPlayer2) {
        if(sPlayer1 == null) {
            sPlayer1 = "Bot1";
        }

        if(sPlayer2 == null) {
            sPlayer2 = "Bot2";
        }

        Player player1 = new Player(sPlayer1);
        Player player2 = new Player(sPlayer2);

        gameManager.setPlayer1(player1);
        gameManager.setPlayer2(player2);
    }

    public boolean checkIfTextFieldIsCorrect(TextField textField, TextField textField2){
        int minLength = 3;
        int maxLength = 15;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Foute Syntax");

        if(alert.isShowing()){alert.close();}

        if(textField.getText().isEmpty() || textField2.getText().isEmpty()) {
            alert.setHeaderText("Je moet een naam invullen!");
            alert.showAndWait();
            return false;
        }

        if(textField.getText().equals(textField2.getText())) {
            alert.setHeaderText("Namen mogen niet gelijk zijn!");
            alert.showAndWait();
            return false;
        }

        if(textField.getLength() < minLength || textField2.getLength() <  minLength ||
                textField.getLength() > maxLength || textField2.getLength() > maxLength) {
            alert.setHeaderText("Names must be between 3 and 15 characters long.");
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

    public boolean bothPlayersReady() {
        return player1.getBoard().isAllShipsPlaced() && player2.getBoard().isAllShipsPlaced();
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

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }
}
