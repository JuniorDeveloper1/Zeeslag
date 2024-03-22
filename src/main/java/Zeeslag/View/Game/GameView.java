package Zeeslag.View.Game;

import Zeeslag.Model.Core.MusicPlayer;
import Zeeslag.Model.GameManager;
import Zeeslag.Model.helper.MVPView;
import Zeeslag.Model.helper.SceneUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;

public class GameView extends VBox implements MVPView {
    private Label title;

    private TextField playName1Field;
    private Label player1;

    private TextField playName2Field;
    private Label player2;

    private Button playButton;
    private HBox configurationBox;

    private Text configurationText;

    private  Label boardSizeLabel;
    private ObservableList<Integer> boardOptions;

    private ComboBox boardSizes;

    private Label amountOfShipsLabel;
    private ObservableList<Integer> amountOfShipsOptions;
    private ComboBox amountOfShips;

    private VBox amountOfShipsBox;

    private VBox boardSizeBox;

    private GameManager gameManager = GameManager.getInstance();
    private Button returnButton;

    public GameView () {
        this.initialize();
        this.initializeNodes();
        this.layoutNodes();
    }

    @Override
    public void initialize() {
        setPrefWidth(800);
        setPrefHeight(531);
    }

    @Override
    public void initializeNodes() {
        title = new Label("Enter your names!");

        player1 = new Label("Player 1:");
        playName1Field = new TextField();
        playName1Field.setPromptText("Enter player 1's name");

        player2 = new Label("Player 2:");
        playName2Field = new TextField();
        playName2Field.setPromptText("Enter player 2's name");

        configurationText = new Text("Configuration");
        configurationBox = new HBox();


         boardSizeLabel= new Label("Board Size:");

        // Dropdown menu for board sizes
        boardOptions = FXCollections.observableArrayList(10, 5, 8, 12);
        boardSizes = new ComboBox<>(boardOptions);

        amountOfShipsLabel = new Label("Amount of ships:");

        amountOfShipsOptions = FXCollections.observableArrayList(5, 4, 3, 2, 1);
        amountOfShips = new ComboBox<>(amountOfShipsOptions);

        boardSizeBox = new VBox();

        amountOfShipsBox = new VBox();

        playButton = new Button("play");
        returnButton = new Button("return");


    }

    @Override
    public void layoutNodes() {
        setAlignment(Pos.TOP_CENTER);
        setSpacing(20);
        title.setFont(Font.font("Arial", 60));

        player2.setFont(Font.font("Arial", 40));
        player1.setFont(Font.font("Arial", 40));
        configurationText.setFont(Font.font("Arial", 20));

        configurationBox.setAlignment(Pos.TOP_CENTER);

        playName1Field.setPrefWidth(300);
        playName1Field.setMaxWidth(300);

        playName2Field.setPrefWidth(300);
        playName2Field.setMaxWidth(300);

        boardSizeLabel.setPadding(new Insets(0, 10, 0, 0));
        boardSizes.setValue(boardOptions.get(0));

        amountOfShipsLabel.setPadding(new Insets(0, 10, 0, 0));
        amountOfShips.setValue(amountOfShipsOptions.get(0));

        boardSizeBox.setSpacing(10);
        boardSizeBox.setAlignment(Pos.CENTER);


        amountOfShipsBox.setSpacing(10);
        amountOfShipsBox.setAlignment(Pos.CENTER);

        boardSizeBox.getChildren().addAll(boardSizeLabel, boardSizes);
        amountOfShipsBox.getChildren().addAll(amountOfShipsLabel, amountOfShips);

        configurationBox.getChildren().addAll(boardSizeBox, amountOfShipsBox);

        SceneUtil.setButtonStyles(playButton);
        SceneUtil.setButtonHoverEffects(playButton);

        SceneUtil.setButtonStyles(returnButton);
        SceneUtil.setButtonHoverEffects(returnButton);

        // Add all nodes to the VBox
        getChildren().addAll(title, player1, playName1Field, player2, playName2Field,
                configurationText, configurationBox, playButton, returnButton);
    }
    public Button getPlayButton() {
        return playButton;
    }

    public TextField getPlayName1Field() {
        return playName1Field;
    }

    public TextField getPlayName2Field() {
        return playName2Field;
    }

    public ComboBox getBoardSizes() {
        return boardSizes;
    }

    public Button getReturnButton() {
        return returnButton;
    }

    public MusicPlayer getMusicPlayer() {
        return gameManager.getMusicPlayer();
    }

    public ComboBox getAmountOfShips() {
        return amountOfShips;
    }
}
