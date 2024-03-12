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

public class Player1Model {
    private GameManager gameManager = GameManager.getInstance();
    private Board board = gameManager.getPlayer1().getBoard();

    public Player1Model() {
        gameManager.getPlayer1().getBoard().setPieces(createPieces());
    }

    private List<Piece> createPieces() {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(new Piece(1, 4));
        pieces.add(new Piece(1, 3));
        pieces.add(new Piece(1, 3));
        pieces.add(new Piece(1, 2));
        pieces.add(new Piece(1, 2));
        return pieces;
    }

    double initialX = 0;
    double initialY = 0;
    public void setShipDragged(ImageView ship, int index, Player1View view) {
        Piece piece = gameManager.getPlayer1().getBoard().getPieces().get(index);
        piece.setSelected(true);

        int cellSize = Player1View.CELL_SIZE;

        Translate translate = new Translate(0, 0);
        ship.getTransforms().add(translate);

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
            double x = translate.getX();
            double y = translate.getY();

            // Calculate the new position based on cell size
            double newX = Math.round(x / cellSize) * cellSize;
            double newY = Math.round(y / cellSize) * cellSize;

            // Check if the new position is within the grid
            if (newX >= 0 && newX + ship.getBoundsInParent().getWidth() <= Player1View.WIDTH &&
                    newY >= 0 && newY + ship.getBoundsInParent().getHeight() <= Player1View.HEIGHT) {
                // Adjust the coordinates to match expected indexing system
                int xCoord = (int) (newX / cellSize) + 1;
                int yCoord = (int) (newY / cellSize) + 1;

                // Update piece coordinates
                piece.setCoord(new Coord(xCoord, yCoord));

                // Mark the piece as placed
                piece.setSelected(false);
                piece.setPlaced(true);

                // Output the adjusted coordinates
                System.out.println("Ship coordinates: (" + xCoord + ", " + yCoord + ")");
            } else {
                // Handle the case where the ship is out of bounds
                System.out.println("Ship coordinates: (OUTOFBOUNDS)");
            }
        });
    }

    // model.getPieceList().get(0).setCoord(
    // new Coord(
    // view.getShip_destroyer().getLayoutX()
    // , view.getShip_destroyer().getLayoutY()));
    public void setCoords(int index, ImageView imageView, Player1View view) {
        int cellSize = Player1View.CELL_SIZE;

        // Bereken de coördinaten op basis van de positie van de ImageView binnen het bord
        int xCoord = (int) (imageView.getLayoutX() / cellSize) + 1;
        int yCoord = (int) (imageView.getLayoutY() / cellSize) + 1;

        // Controleer of de coördinaten binnen het bereik van het bord vallen
        if (xCoord >= 1 && xCoord <= board.getWidth() && yCoord >= 1 && yCoord <= board.getHeight()) {
            // Stel de coördinaten van het schip in op basis van de berekende waarden
            gameManager.getPlayer1().getBoard().getPieces().get(index).setCoord(new Coord(xCoord, yCoord));
            System.out.println("Ship coordinates: (" + xCoord + ", " + yCoord + ")");
        } else {
            // Geef een foutmelding als de ImageView buiten het bereik van het bord wordt geplaatst
            System.out.println("Ship coordinates: (OUTOFBOUNDS)");
        }
    }



    public GameManager getGameManager() {
        return gameManager;
    }
}
