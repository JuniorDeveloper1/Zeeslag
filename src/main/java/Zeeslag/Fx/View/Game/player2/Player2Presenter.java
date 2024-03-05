package Zeeslag.Fx.View.Game.player2;

import Zeeslag.Core.Board.Board;
import Zeeslag.Core.Coord.Coord;
import Zeeslag.Fx.Manager.Presenter;
import Zeeslag.Fx.Manager.SceneUtil;
import Zeeslag.Fx.Model.Player2Model;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;

public class Player2Presenter implements Presenter {

    private Player2Model model;
    private Player2View view;

    public Player2Presenter(Player2Model model, Player2View view) {
        this.model = model;
        this.view = view;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        Board playerBoard = model.getGameManager().getPlayer2().getBoard();

        view.getStart().setOnAction(actionEvent -> {
            if (playerBoard.checkIfPiecesArePlaced()) {
                model.getGameManager().getPlayer2().getBoard().setStart(true);
                view.getStart().setVisible(false);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Something went wrong");
                alert.setHeaderText("Boten niet geplaatst!");
                alert.setContentText("Plaats al de boten voordat het spelt begint!");
                alert.showAndWait();
            }
        });

        if (!(playerBoard.isStart())) {
            model.setShipDragged(view.getShip_destroyer(), 0);
            model.setShipDragged(view.getShip_battleship(), 1);
            model.setShipDragged(view.getShip_cruiser(), 2);
            model.setShipDragged(view.getShip_patrol1(), 3);
            model.setShipDragged(view.getShip_patrol2(), 4);
        }

        Platform.runLater(() -> {
            model.setCoords(0, view.getShip_destroyer());
            model.setCoords(1, view.getShip_battleship());
            model.setCoords(2, view.getShip_cruiser());
            model.setCoords(3, view.getShip_patrol1());
            model.setCoords(4, view.getShip_patrol2());
        });
    }

    public void addWindowEventHandler() {
        SceneUtil.closeSceneWarning(view.getScene());
    }

    private void updateView() {
        String name = model.getGameManager().getPlayer2().getName() + "'s screen";
        view.getPlayerName().setText(name);
    }

    public Player2Model getModel() {
        return model;
    }

    @Override
    public Node getView() {
        return view;
    }
}
