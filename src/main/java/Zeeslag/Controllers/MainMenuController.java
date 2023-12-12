package Zeeslag.Controllers;

import Zeeslag.GameManager;
import Zeeslag.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuController {
    public GameManager gameManager = GameManager.getInstance();
    public Button play;

    @FXML
    public void initialize(){

    }

    public void play() throws IOException {
        String player1 = "";
        String player2 = "";


        createHero(player1, player2);
        gameManager.openScene("game_controller.fxm", null);
        gameManager.closeScene(play);
        //gameManager.getGame().loadDefault();
    }

    private void createHero(String player1, String player2) {
        if(player1 == null) {
            player1 = "Bot1";
        }

        if(player2 == null) {
            player2 = "Bot2";
        }

        gameManager.setPlayer1(new Player(player1, gameManager.getPlayer1().getBoard()));
        gameManager.setPlayer2(new Player(player2, gameManager.getPlayer1().getBoard()));
    }
}
