package Zeeslag.View.Player1;

import Zeeslag.Model.Core.Board;
import Zeeslag.Model.Core.Cell;
import Zeeslag.Model.Core.MusicPlayer;
import Zeeslag.Model.GameManager;
import Zeeslag.Model.helper.MVPView;
import Zeeslag.Model.helper.SceneUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;

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


        for (int y = 0; y < opponentBoard.getSizeBoard(); y++) {
            for (int x = 0; x < opponentBoard.getSizeBoard(); x++) {
                Cell cell = opponentBoard.getCells()[x][y];
                opponentGridPane.add(cell, x, y);
            }
        }

        for (int y = 0; y < board.getSizeBoard(); y++) {
            for (int x = 0; x < board.getSizeBoard(); x++) {
                Cell cell = board.getCells()[x][y];
                gridPane.add(cell, x, y);
            }
        }

        opponentGridPane.setMaxWidth(Region.USE_PREF_SIZE);
        gridPane.setMaxWidth(Region.USE_PREF_SIZE);

        opponentGridPane.setMinWidth(Region.USE_PREF_SIZE);
        gridPane.setMinWidth(Region.USE_PREF_SIZE);

        playerBox.setAlignment(Pos.TOP_CENTER);
        playerBox.setSpacing(20);
        playerBox.setPadding(new Insets(20));

        playerBox.getChildren().addAll(playerName, waitingForOtherPlayer, opponentGridPane, gridPane, start);

        horizontal.setSpacing(30);
        horizontal.setAlignment(Pos.BOTTOM_CENTER);

        SceneUtil.setButtonStyles(start);
        SceneUtil.setButtonHoverEffects(start);
        start.setAlignment(Pos.CENTER);

        getChildren().addAll(playerBox, horizontal);
        horizontal.getChildren().add(start);
        start.toFront();
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

    public MusicPlayer getMusicPlayer() {
        return gameManager.getMusicPlayer();
    }
}
