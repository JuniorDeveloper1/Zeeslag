package Zeeslag.fx.View.Game.player1;

import Zeeslag.fx.Manager.MVPView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Player1View extends VBox implements MVPView {
    private Text playerName;

    private HBox horizontal;
    private ImageView gridImage;
    private ImageView ship_battleship;
    private ImageView ship_cruiser;
    private ImageView ship_destroyer;
    private ImageView ship_patrol1;
    private ImageView ship_patrol2;

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
        Image imageLink = new Image("sea_battle_grid.png");
        this.gridImage = new ImageView(imageLink);

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
        playerName.setFont(Font.font("Arial", 15));
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(playerName,gridImage,
                horizontal);

        horizontal.getChildren().addAll(ship_battleship,
                ship_cruiser,
                ship_destroyer,
                ship_patrol1,
                ship_patrol2);

        horizontal.setAlignment(Pos.CENTER);
    }

    public Text getPlayerName() {
        return playerName;
    }
}
