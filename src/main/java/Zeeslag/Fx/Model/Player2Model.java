package Zeeslag.Fx.Model;

import Zeeslag.Core.Game.GameManager;
import javafx.fxml.FXML;

public class Player2Model {
    private GameManager gameManager = GameManager.getInstance();

    @FXML
    public void initialize(){

    }

    /**
     * Logic for player..
     */

    public GameManager getGameManager() {
        return gameManager;
    }


}
