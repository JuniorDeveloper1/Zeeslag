package Zeeslag.fx.View.Game.player1;

import Zeeslag.fx.Manager.MVPView;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Player1View extends VBox implements MVPView {
    private Text playerName;
    private ImageView gridImage;

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

    }

    @Override
    public void layoutNodes() {
        playerName.setFont(Font.font("Arial", 15));
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(playerName,gridImage);
    }

    public Text getPlayerName() {
        return playerName;
    }
}
