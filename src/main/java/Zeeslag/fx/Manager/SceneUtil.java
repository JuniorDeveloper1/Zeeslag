package Zeeslag.fx.Manager;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public abstract class SceneUtil {
    public static void openView(Presenter presenter, String title) {
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene((Parent) presenter.getView()));
        primaryStage.setTitle(title);
        primaryStage.show();
    }

    public static void closeScene(Button button){
        Stage currentStage = (Stage) button.getScene().getWindow();
        currentStage.close();
    }

    public static void closeScene(Stage stage) { stage.close();}

}
