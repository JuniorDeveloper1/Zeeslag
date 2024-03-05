package Zeeslag.Fx.View.Game.player1;

import Zeeslag.Fx.Manager.MVPView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Player1View extends VBox implements MVPView {
    private Text playerName;
    private VBox playerBox;
    private Scene scene;

    private GridPane gridPane;
    private HBox horizontal;

    public static final int CELL_SIZE = 50;
    private ImageView ship_battleship;
    private ImageView ship_cruiser;
    private ImageView ship_destroyer;
    private ImageView ship_patrol1;
    private ImageView ship_patrol2;

    private Button start;
    public static final int WIDTH = 531;
    public static final int HEIGHT = 800;

    public Player1View() {
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


        this.start = new Button("start");

        this.horizontal = new HBox(30);
    }

    @Override
    public void layoutNodes() {
        playerName.setFont(Font.font("Arial",25));

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Rectangle rectangle = new Rectangle(CELL_SIZE, CELL_SIZE, Color.WHITE);
                rectangle.setStroke(Color.BLACK);
                gridPane.add(rectangle, x, y);
            }
        }

        gridPane.setAlignment(Pos.TOP_CENTER);
        playerBox.setAlignment(Pos.TOP_CENTER);
        playerBox.getChildren().addAll(playerName, gridPane, start);

        horizontal.setSpacing(30);
        horizontal.setAlignment(Pos.BOTTOM_CENTER);

        setButtonStyles(start);
        setButtonHoverEffects(start);
        start.setAlignment(Pos.CENTER);


        getChildren().addAll(playerBox, horizontal);
        horizontal.getChildren().addAll(ship_battleship, ship_cruiser, ship_destroyer, ship_patrol1, ship_patrol2);
        horizontal.getChildren().add(start);
        start.toFront();
    }

    private void setButtonStyles(Button button) {
        button.setStyle("-fx-background-color: #1e90ff; -fx-text-fill: white; -fx-font-size: 20px; ");
        button.setMinWidth(150);
    }
    public GridPane getGridPane() {
        return gridPane;
    }

    private void setButtonHoverEffects(Button button) {
        button.setOnMouseEntered(e -> button.setScaleX(1.2));
        button.setOnMouseExited(e -> button.setScaleX(1));
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

    public Button getStart() {
        return start;
    }

    public HBox getHorizontal() {
        return horizontal;
    }

}