package Zeeslag.Model.helper;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

}
