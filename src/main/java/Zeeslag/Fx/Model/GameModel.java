package Zeeslag.Fx.Model;

import Zeeslag.Core.Game.GameManager;
import Zeeslag.Core.Player.Player;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class GameModel {
    private  GameManager gameManager = GameManager.getInstance();

    public void play(String player1, String player2) {
        createHero(player1, player2);
        gameManager.startGame(player1, player2);
        System.out.println(gameManager.getPlayer1().getName());
    }

    private void createHero(String sPlayer1, String sPlayer2) {
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


    public GameManager getGameManager() {
        return gameManager;
    }
}
