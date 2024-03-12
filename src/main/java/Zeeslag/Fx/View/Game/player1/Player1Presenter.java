package Zeeslag.Fx.View.Game.player1;

import Zeeslag.Fx.Manager.Presenter;
import Zeeslag.Fx.Manager.SceneUtil;
import Zeeslag.Fx.Model.Player1Model;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
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
            if(mouseEvent.getButton() == MouseButton.PRIMARY) {
                double cellWidth = view.getGridPane().getWidth() / 10;
                double cellHeight = view.getGridPane().getHeight() / 10;

                double mouseX = mouseEvent.getX();
                double mouseY = mouseEvent.getY();

                int x = (int) (mouseX / cellWidth);
                int y = (int) (mouseY / cellHeight);

                System.out.println("Mouse coordinates: (" + mouseX + ", " + mouseY + ")");
                System.out.println("Clicked cell coordinates: (" + x + ", " + y + ")");

                boolean successfulPlaced = model.placeShip(x, y);

                if (!successfulPlaced) {
                    SceneUtil.showAlert("Ship misplaced", "You cannot add a ship there!");
                }
            }


        });
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void addWindowEventHandler() {
        // Add any window event handlers if needed
    }

    @Override
    public Node getView() {
        return view;
    }
}
