package Zeeslag.Fx.Model;

import Zeeslag.Core.Game.GameManager;
import javafx.fxml.FXML;

public class MainMenuModel  {
    public GameManager gameManager = GameManager.getInstance();

    public MainMenuModel() {
        initialize();
    }
    @FXML
    public void initialize(){
            gameManager.startGame();
    }
}
