package Zeeslag.View.Win;

import Zeeslag.Model.Core.MusicPlayer;
import Zeeslag.Model.GameManager;
import Zeeslag.Model.helper.MVPView;
import Zeeslag.Model.helper.SceneUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WinView extends VBox implements MVPView {
    private Text playerName;
    private Button continueGame;
    private GameManager gameManager = GameManager.getInstance();
    public static final int WIDTH = 531;
    public static final int HEIGHT = 800;

    public WinView() {
        this.initialize();
        this.initializeNodes();
        this.layoutNodes();
    }

    @Override
    public void initialize() {
        setPrefWidth(WIDTH);
        setPrefHeight(HEIGHT);
    }

    @Override
    public void initializeNodes() {
        playerName = new Text("Bot");
        continueGame = new Button("Continue");
    }

    @Override
    public void layoutNodes() {
        setAlignment(Pos.TOP_CENTER);
        playerName.setFont(Font.font("Arial", 50));
        playerName.setFill(Color.BLACK);

        SceneUtil.setButtonStyles(continueGame);
        SceneUtil.setButtonHoverEffects(continueGame);

        setSpacing(20); // Add spacing between playerName and continueGame

        getChildren().addAll(playerName, continueGame);
    }

    public MusicPlayer getMusicPlayer() {
        return gameManager.getMusicPlayer();
    }

    public Text getPlayerName() {
        return playerName;
    }

    public Button getContinueGame() {
        return continueGame;
    }
}
