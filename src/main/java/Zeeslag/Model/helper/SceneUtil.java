package Zeeslag.Model.helper;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public abstract class SceneUtil {
    /**
     * This class provides help to other classes
     * These methodes are used allot but is dirty to rewrite it everytime.

     *includes methods to openviews and to show alerts.

     */

    public static void openView(Presenter presenter) {
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene((Parent) presenter.getView()));
        primaryStage.setTitle("Zeeslag");
        primaryStage.show();
    }

    public static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void setButtonStyles(Button button) {
        button.setStyle("-fx-background-color: #1e90ff; -fx-text-fill: white; -fx-font-size: 20px; ");
        button.setMinWidth(150);
    }

    public static void setButtonHoverEffects(Button button) {
        button.setOnMouseEntered(e -> button.setScaleX(1.2));
        button.setOnMouseExited(e -> button.setScaleX(1));
    }



    public static void stopBackgroundMusic(MediaPlayer mediaPlayer) {
        if(mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

}
