package Zeeslag.View.Game;

import Zeeslag.Model.helper.MVPView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

import java.io.File;

public class GameView extends VBox implements MVPView {
    private Label title;

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

        player1 = new Label("Player 1:");


        playName1Field = new TextField();
        playName1Field.setPromptText("Enter player 1's name");

        player2 = new Label("Player 2:");


        playName2Field = new TextField();
        playName2Field.setPromptText("Enter player 2's name");

        playButton = new Button("play");

        playBackgroundMusic();
    }

    @Override
    public void layoutNodes() {
        setAlignment(Pos.TOP_CENTER);
        setSpacing(20);
        title.setFont(Font.font("Arial", 60));


        player2.setFont(Font.font("Arial", 40));

        player1.setFont(Font.font("Arial", 40));

        this.getPlayName1Field().setPrefWidth(300);
        this.getPlayName1Field().setMaxWidth(300);

        this.getPlayName2Field().setPrefWidth(300);
        this.getPlayName2Field().setMaxWidth(300);

        setButtonStyles(playButton);
        setButtonHoverEffects(playButton);

        getChildren().addAll(title,  player1, playName1Field, player2, playName2Field, playButton);
    }

    private void setButtonStyles(Button button) {
        button.setStyle("-fx-background-color: #1e90ff; -fx-text-fill: white; -fx-font-size: 20px; ");
        button.setMinWidth(150);
    }
    private void setButtonHoverEffects(Button button) {
        button.setOnMouseEntered(e -> button.setScaleX(1.2));
        button.setOnMouseExited(e -> button.setScaleX(1));
    }


    private void playBackgroundMusic() {
        audioFilePath = "resources/awesomeness.wav";
        File file = new File(audioFilePath);
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public void stopBackgroundMusic(){
        if(mediaPlayer != null) {
            mediaPlayer.stop();
        }
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
