package Zeeslag;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    GameManager gameManager = GameManager.getInstance();
    @Override
    public void start(Stage stage) throws Exception {
        gameManager.openScene("main_menu.fxml", "Zeeslag");
    }
}