package Zeeslag.fx.View.Game;

import Zeeslag.fx.Manager.MVPView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;

public class GameView extends VBox implements MVPView {
    private Label title;

    private Label errorLabel;

    private TextField playName1Field;
    private Label player1;

    private TextField playName2Field;
    private Label player2;

    private Button playButton;
    private String audioFilePath;
    private MediaPlayer mediaPlayer;

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
    public void initializeNodes(){
        title = new Label("Enter your names!");


        this.errorLabel = new Label("errorLabel");

        player1 = new Label("Player 1:");


        playName1Field = new TextField();
        playName1Field.setPromptText("Enter player 1's name");

        player2 = new Label("Player 2:");


        playName2Field = new TextField();
        playName2Field.setPromptText("Enter player 2's name");

        playButton = new Button("Play");

        playBackgroundMusic();
    }

    @Override
    public void layoutNodes() {
        setAlignment(Pos.TOP_CENTER);
        setSpacing(20);
        title.setFont(Font.font("Arial", 60));

        errorLabel.setFont(Font.font("Arial", FontWeight.BOLD,  25));
        errorLabel.setTextFill(Color.RED);

        errorLabel.setText("");


        player2.setFont(Font.font("Arial", 40));

        player1.setFont(Font.font("Arial", 40));

        this.getPlayName1Field().setPrefWidth(300);
        this.getPlayName1Field().setMaxWidth(300);

        this.getPlayName2Field().setPrefWidth(300);
        this.getPlayName2Field().setMaxWidth(300);

        setButtonStyles(playButton);

        getChildren().addAll(title, errorLabel, player1, playName1Field, player2, playName2Field, playButton);
    }

    private void setButtonStyles(Button button) {
        button.setStyle("-fx-background-color: #1e90ff; -fx-text-fill: white; -fx-font-size: 20px; ");
        button.setMinWidth(150);
    }

    private void playBackgroundMusic() {
        audioFilePath = "resources/awesomeness.wav";
        File file = new File(audioFilePath);
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public Label getErrorLabel() {
        return errorLabel;
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
}
