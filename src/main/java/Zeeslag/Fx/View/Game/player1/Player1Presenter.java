package Zeeslag.Fx.View.Game.player1;

import Zeeslag.Fx.Manager.Presenter;
import Zeeslag.Fx.Model.Player1Model;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

public class Player1Presenter implements Presenter {
    private final Player1Model model;
    private final Player1View view;

    public Player1Presenter(Player1Model model, Player1View view) {
        this.model = model;
        this.view = view;
        initializeEventHandlers();
    }

        private void initializeEventHandlers() {
            view.getGridPane().setOnMouseClicked(mouseEvent -> {
                double cellWidth = view.getGridPane().getWidth() / 10;
                double cellHeight = view.getGridPane().getHeight() / 10;

                double mouseX = mouseEvent.getX();
                double mouseY = mouseEvent.getY();

                int x = (int) (mouseX / cellWidth);
                int y = (int) (mouseY / cellHeight);

                System.out.println("Mouse coordinates: (" + mouseX + ", " + mouseY + ")");
                System.out.println("Clicked cell coordinates: (" + x + ", " + y + ")");

                boolean placementSuccessful = model.placeShip(x, y);
                if (placementSuccessful) {
                    System.out.println("Ship placed successfully");
                } else {
                    showAlert("Invalid Placement", "The ship cannot be placed here.");
                }
            });
        }








    public void addWindowEventHandler() {
        // Add any window event handlers if needed
    }

    @Override
    public Node getView() {
        return view;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
