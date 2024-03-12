package Zeeslag.Fx.View.Game.player1;

import Zeeslag.Core.Board.Board;
import Zeeslag.Core.Coord.Coord;
import Zeeslag.Core.Piece.Piece;
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
            model.setShipDragged(view.getShip_destroyer(), 0, view);
            model.setShipDragged(view.getShip_battleship(),1, view);
            model.setShipDragged(view.getShip_cruiser(),2, view);
            model.setShipDragged(view.getShip_patrol1(),3, view);
            model.setShipDragged(view.getShip_patrol2(),4, view);
        }



        Platform.runLater(() -> {
            model.setCoords(0, view.getShip_destroyer(), view);
            model.setCoords(1, view.getShip_battleship(), view);
            model.setCoords(2, view.getShip_cruiser(), view);
            model.setCoords(3, view.getShip_patrol1(), view);
            model.setCoords(4, view.getShip_patrol2(), view);

            // Output the coordinates for debugging purposes
            for (Piece piece : model.getGameManager().getPlayer1().getBoard().getPieces()) {
                System.out.println("Coords: " + piece.getCoord());
            }
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
