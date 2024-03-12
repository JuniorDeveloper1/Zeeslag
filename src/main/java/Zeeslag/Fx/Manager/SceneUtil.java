package Zeeslag.Fx.Manager;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public abstract class SceneUtil {

    public static void openView(Presenter presenter) {
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene((Parent) presenter.getView()));
        primaryStage.setTitle("Zeeslag");
        primaryStage.show();
    }

    public static void closeScene(Button button){
        Stage currentStage = (Stage) button.getScene().getWindow();
        currentStage.close();
    }

    public static void closeSceneWarning(Scene scene) {
        scene.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Hierdoor stopt het spel!");
                alert.setContentText("Ben je zeker?");
                alert.setTitle("Opgelet!");
                alert.getButtonTypes().clear();
                ButtonType neen = new ButtonType("Neen");
                ButtonType ja = new ButtonType("Ja");
                alert.getButtonTypes().addAll(neen, ja);
                alert.showAndWait();
                if (alert.getResult() == null || alert.getResult().equals(neen)) {
                    event.consume();
                }
            }
        });
    }

    public static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void closeScene(Stage stage) { stage.close();}

}
