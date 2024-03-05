package Zeeslag.Fx.View.Game.player1;

import Zeeslag.Core.Board.Board;
import Zeeslag.Core.Coord.Coord;
import Zeeslag.Core.Player.Player;
import Zeeslag.Fx.Manager.Presenter;
import Zeeslag.Fx.Manager.SceneUtil;
import Zeeslag.Fx.Model.Player1Model;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;

public class Player1Presenter implements Presenter {

    private Player1Model model;
    private Player1View view;


    public Player1Presenter(Player1Model model, Player1View view) {
        this.model = model;
        this.view = view;

        this.addEventHandlers();
        this.updateView();


    }

    private void addEventHandlers() {
        Board playerBoard = model.getGameManager().getPlayer1().getBoard();

        view.getStart().setOnAction(actionEvent -> {
            if (playerBoard.checkIfPiecesArePlaced()) {
                model.getGameManager().getPlayer1().getBoard().setStart(true);
                view.getStart().setVisible(false);
                System.out.println("Al de boten zijn geplaats! We gaan vertrekken...");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Something went wrong");
                alert.setHeaderText("Boten niet geplaatst!");
                alert.setContentText("Plaats al de boten voordat het spelt begint!");
                alert.showAndWait();
            }
        });


        if(!(playerBoard.isStart())) {
            model.setShipDragged(view.getShip_destroyer(), 0);
            model.setShipDragged(view.getShip_battleship(),1);
            model.setShipDragged(view.getShip_cruiser(),2);
            model.setShipDragged(view.getShip_patrol1(),3);
            model.setShipDragged(view.getShip_patrol2(),4);
        }



        Platform.runLater(() -> {
            model.setCoords(0, view.getShip_destroyer());
            model.setCoords(1, view.getShip_battleship());
            model.setCoords(2, view.getShip_cruiser());
            model.setCoords(3, view.getShip_patrol1());
            model.setCoords(4, view.getShip_patrol2());
            System.out.println("Cords Presenter: " + view.getShip_destroyer().getLayoutX() +  " " + view.getShip_destroyer().getLayoutY());
            System.out.println("Coords : " + model.getGameManager().getPlayer1().getBoard().getPieces().get(0).getCoord());
        });



    }

    public void addWindowEventHandler() {
        SceneUtil.closeSceneWarning(view.getScene());
    }


    private void updateView() {
        String name = model.getGameManager().getPlayer1().getName() + "'s screen";
        view.getPlayerName().setText(name);
    }



    public Player1Model getModel() {
        return model;
    }

    @Override
    public Node getView() {
        return view;
    }
}
