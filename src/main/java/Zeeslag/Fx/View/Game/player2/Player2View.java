package Zeeslag.fx.View.Game.player2; // Change package name

import Zeeslag.fx.Manager.MVPView;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Player2View extends StackPane implements MVPView { // Change class name
    private Text playerName;
    private VBox playerBox;

    private GridPane gridPane;
    private HBox horizontal;

    private final int cellSize = 50;
    private ImageView[][] gridCells;
    private ImageView ship_battleship;
    private ImageView ship_cruiser;
    private ImageView ship_destroyer;
    private ImageView ship_patrol1;
    private ImageView ship_patrol2;

    public Player2View() {
        this.initialize();
        this.initializeNodes();
        this.layoutNodes();
    }

    @Override
    public void initialize() {
        this.setPrefWidth(531);
        this.setPrefHeight(800);
    }

    @Override
    public void initializeNodes() {
        this.playerName = new Text();
        this.playerName.setFont(Font.font("Arial", 15));
        this.playerName.setFill(Color.BLACK);

        this.gridPane = new GridPane();
        this.gridCells = new ImageView[10][10];

        this.playerBox = new VBox();

        Image battleship = new Image("ShipBattleshipHull.png");
        this.ship_battleship = new ImageView(battleship);

        Image cruiser = new Image("ShipCruiserHull.png");
        this.ship_cruiser = new ImageView(cruiser);

        Image destroyer = new Image("ShipDestroyerHull.png");
        this.ship_destroyer = new ImageView(destroyer);

        Image patrol1 = new Image("ShipPatrolHull.png");
        this.ship_patrol1 = new ImageView(patrol1);

        Image patrol2 = new Image("ShipPatrolHull.png");
        this.ship_patrol2 = new ImageView(patrol2);

        this.horizontal = new HBox(30);
    }

    @Override
    public void layoutNodes() {
        playerName.setFont(Font.font("Arial",25));

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Rectangle rectangle = new Rectangle(cellSize, cellSize, Color.WHITE);
                rectangle.setStroke(Color.BLACK);
                gridPane.add(rectangle, x, y);
            }
        }


        playerBox.setAlignment(Pos.TOP_CENTER);
        playerBox.getChildren().addAll(playerName, gridPane);

        horizontal.setSpacing(30);
        horizontal.setAlignment(Pos.BOTTOM_CENTER);

        // Add nodes to the StackPane
        getChildren().addAll(playerBox, horizontal);
        horizontal.getChildren().addAll(ship_battleship, ship_cruiser, ship_destroyer, ship_patrol1, ship_patrol2);
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public ImageView[][] getGridCells() {
        return gridCells;
    }

    public Text getPlayerName() {
        return playerName;
    }

    public VBox getPlayerBox() {
        return playerBox;
    }

    public ImageView getShip_battleship() {
        return ship_battleship;
    }

    public ImageView getShip_cruiser() {
        return ship_cruiser;
    }

    public ImageView getShip_destroyer() {
        return ship_destroyer;
    }

    public ImageView getShip_patrol1() {
        return ship_patrol1;
    }

    public ImageView getShip_patrol2() {
        return ship_patrol2;
    }
}
