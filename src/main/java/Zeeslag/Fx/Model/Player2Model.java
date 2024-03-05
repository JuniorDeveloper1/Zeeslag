package Zeeslag.Fx.Model;

import Zeeslag.Core.Board.Board;
import Zeeslag.Core.Coord.Coord;
import Zeeslag.Core.Game.GameManager;
import Zeeslag.Core.Piece.Piece;
import Zeeslag.Fx.View.Game.player1.Player1View;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.List;

public class Player2Model {
    private GameManager gameManager = GameManager.getInstance();

    public Player2Model() {
    }



    double initialX = 0;
    double initialY = 0;

    public void setShipDragged(ImageView ship, int index) {
        Piece piece = gameManager.getPlayer2().getBoard().getPieces().get(index);
        Board board = gameManager.getPlayer2().getBoard();
        piece.setSelected(true);
        int cellSize = Player1View.CELL_SIZE;
        int screenWidht = Player1View.WIDTH;
        int screenHeight = -20;
        Translate translate = new Translate(0, 0);
        ship.getTransforms().add(translate);

        ship.setOnDragDetected(mouseEvent -> {
            ship.startFullDrag();
        });

        ship.setOnMousePressed(mouseEvent -> {
            initialX = mouseEvent.getX();
            initialY = mouseEvent.getY();
        });

        ship.setOnMouseDragged(mouseEvent -> {
            double x = mouseEvent.getX() - initialX;
            double y = mouseEvent.getY() - initialY;

            translate.setX(translate.getX() + x);
            translate.setY(translate.getY() + y);
        });

        ship.setOnMouseReleased(mouseEvent -> {
            double x = mouseEvent.getX() - initialX;
            double y = mouseEvent.getY() - initialY;

            translate.setX(translate.getX() + x);
            translate.setY(translate.getY() + y);

            double xOffset = (cellSize - ship.getBoundsInLocal().getWidth()) / 2;
            double yOffset = (cellSize - ship.getBoundsInLocal().getHeight()) / 2;

            double xCord = Math.round((translate.getX() - xOffset) / cellSize) * cellSize + xOffset;
            double yCord = Math.round((translate.getY() - yOffset) / cellSize) * cellSize + yOffset;

            if (!(xCord >= screenWidht - ship.getBoundsInLocal().getWidth() ||
                    yCord >= screenHeight - ship.getBoundsInLocal().getHeight())) {
                translate.setX(xCord);
                translate.setY(yCord);
                piece.setSelected(false);
                piece.setPlaced(true);
            } else {
                translate.setX(0);
                translate.setY(0);
                piece.setSelected(false);
            }
            piece.setCoord(new Coord((int) translate.getX(), (int) translate.getY()));

            ship.setMouseTransparent(false);
        });
    }

    public void setCoords(int index, ImageView imageView) {
        gameManager.getPlayer2().getBoard().getPieces().get(index).setCoord(
                new Coord(
                        (int) imageView.getLayoutX(),
                        (int) imageView.getLayoutY()
                )
        );
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
