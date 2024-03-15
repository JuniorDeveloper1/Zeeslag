package Zeeslag.View.Game.player1;

import Zeeslag.Model.Core.Board;
import Zeeslag.Model.Core.Cell;
import Zeeslag.Model.GameManager;
import Zeeslag.Model.helper.MVPView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PlayerView extends VBox implements MVPView {
    private GameManager gameManager = GameManager.getInstance();
    private Board board = gameManager.getPlayer1().getBoard();
    private Board opponentBoard = gameManager.getPlayer1().getOpponentBoard();

    private Text playerName;
    private Text waitingForOtherPlayer;
    private VBox playerBox;
    private GridPane opponentGridPane;
    private GridPane gridPane;
    private HBox horizontal;
    private Button start;
    public static final int WIDTH = 531;
    public static final int HEIGHT = 800;

    public PlayerView() {
        this.initialize();
        this.initializeNodes();
        this.layoutNodes();
    }

    @Override
    public void initialize() {
        this.setPrefWidth(WIDTH);
        this.setPrefHeight(HEIGHT);
    }

    @Override
    public void initializeNodes() {
        this.playerName = new Text();
        this.playerName.setFont(Font.font("Arial", 15));
        this.playerName.setFill(Color.BLACK);

        this.waitingForOtherPlayer = new Text("Waiting for other player...");
        this.waitingForOtherPlayer.setFont(Font.font("Arial", 15));
        this.waitingForOtherPlayer.setFill(Color.RED);

        this.opponentGridPane = new GridPane();
        this.gridPane = new GridPane();
        this.playerBox = new VBox();
        this.start = new Button("start");
        this.horizontal = new HBox(30);
    }


    @Override
    public void layoutNodes() {
        setAlignment(Pos.TOP_CENTER);
        playerName.setFont(Font.font("Arial", 25));

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Cell cell = opponentBoard.getCells()[x][y];
                opponentGridPane.add(cell, x, y);
            }
        }

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Cell cell = board.getCells()[x][y];
                gridPane.add(cell, x, y);
            }
        }

        opponentGridPane.setMaxWidth(Region.USE_PREF_SIZE);
        gridPane.setMaxWidth(Region.USE_PREF_SIZE);

        playerBox.setAlignment(Pos.TOP_CENTER);
        playerBox.setSpacing(20); // Add spacing between the children of playerBox
        playerBox.setPadding(new Insets(20)); // Add padding to playerBox

        playerBox.getChildren().addAll(playerName, waitingForOtherPlayer, opponentGridPane, gridPane, start);

        horizontal.setSpacing(30);
        horizontal.setAlignment(Pos.BOTTOM_CENTER);

        setButtonStyles(start);
        setButtonHoverEffects(start);
        start.setAlignment(Pos.CENTER);

        getChildren().addAll(playerBox, horizontal);
        horizontal.getChildren().add(start);
        start.toFront();
    }

    private void setButtonStyles(Button button) {
        button.setStyle("-fx-background-color: #1e90ff; -fx-text-fill: white; -fx-font-size: 20px; ");
        button.setMinWidth(150);
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

    public Button getStart() {
        return start;
    }

    public HBox getHorizontal() {
        return horizontal;
    }

    public GridPane getOpponentGridPane() {
        return opponentGridPane;
    }

    public Text getWaitingForOtherPlayer() {
        return waitingForOtherPlayer;
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
