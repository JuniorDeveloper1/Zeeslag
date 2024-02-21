package Zeeslag.fx.Manager;

import Zeeslag.modulesVerzinBetereNaamXd.Game.GameManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneUtil {
    public static <T>  void  openScene(String fxmlFile, String title, T presenter) throws IOException {
        FXMLLoader loader = FXMLLoader.load(Objects.requireNonNull(GameManager.class.getClassLoader().getResource(fxmlFile)));
        loader.setController(presenter);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) scene.getWindow();
        if (title == null) {
            title = "Default";
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void closeScene(Button button){
        Stage currentStage = (Stage) button.getScene().getWindow();
        currentStage.close();
    }

    public static void closeScene(Stage stage) { stage.close();}
}
