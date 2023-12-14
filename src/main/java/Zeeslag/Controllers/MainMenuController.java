package Zeeslag.Controllers;

import Zeeslag.GameManager;
import Zeeslag.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Scanner;

public class MainMenuController {
    public GameManager gameManager = GameManager.getInstance();
    public Button play;

    @FXML
    public void initialize(){
        gameManager.startGame();
    }

    public void play() throws IOException {
        /**
         * Create hero logic stuff..
         */

        createHero(null, null);

        //System.out.println(gameManager.getPlayer1().getName());
        //System.out.println(gameManager.getPlayer1().getUuid());

        gameManager.openScene("game_controller.fxml", "game"); //Game controller?
        gameManager.closeScene(play);
        //gameManager.getGame().loadDefault();
    }

    private void createHero(String sPlayer1, String sPlayer2) {
        if(sPlayer1 == null) {
            sPlayer1 = "Bot1";
        }

        if(sPlayer2 == null) {
            sPlayer2 = "Bot2";
        }

        Player player1 = new Player(sPlayer1, gameManager.getPlayer1().getBoard());
        Player player2 = new Player(sPlayer2, gameManager.getPlayer2().getBoard());

        gameManager.setPlayer1(player1);
        gameManager.setPlayer2(player2);
    }
}
