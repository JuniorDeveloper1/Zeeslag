package Zeeslag.fx.View.MainMenu;

import Zeeslag.fx.Manager.MVPView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MainMenuView extends VBox implements MVPView {
    private Button playButton;
    private Button leaderboardButton;
    private Button closeButton;
    private  VBox zeeslagTextBox;
    private Text zeeslagText;
    private Image image;
    private BackgroundImage backgroundImage;
    private String audioFilePath;
    private MediaPlayer mediaPlayer;
    public MainMenuView() {
        initialize();
        initializeNodes();
        layoutNodes();
    }

    @Override
    public void initialize() {
        //Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        //screenSize.getWidth()
        //screenSize.getHeight()
        this.setPrefWidth(1000);
        this.setPrefHeight(531);
    }

    @Override
    public void initializeNodes() {
        // Initialize buttons
        zeeslagTextBox= new VBox();
        zeeslagText = new Text("Zeeslag");
        playButton = new Button("Play");
        leaderboardButton = new Button("Leaderboard");
        closeButton = new Button("Close");

        image = new Image("SeaBattleBackground.png");
        backgroundImage = new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);

        //AUDIO
        audioFilePath = "resources/awesomeness.wav";
        File file = new File(audioFilePath);
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    @Override
    public void layoutNodes() {
        zeeslagText.setFont(Font.font("Arial", 60));
        zeeslagText.setFill(Color.WHITE);

        setButtonStyles(playButton);
        setButtonStyles(leaderboardButton);
        setButtonStyles(closeButton);

        setButtonHoverEffects(playButton);
        setButtonHoverEffects(leaderboardButton);
        setButtonHoverEffects(closeButton);

        setButtonCenter(playButton);
        setButtonCenter(leaderboardButton);
        setButtonCenter(closeButton);

        zeeslagTextBox.setAlignment(Pos.CENTER);
        setAlignment(Pos.TOP_CENTER);
        setSpacing(20);

        zeeslagTextBox.setPadding(new Insets(0, 0, 100, 0));

        this.setBackground(new Background(backgroundImage));

        getChildren().addAll(zeeslagTextBox, playButton, leaderboardButton, closeButton);
        zeeslagTextBox.getChildren().addAll(zeeslagText);
    }


    private void setButtonCenter(Button button){
        button.setAlignment(Pos.CENTER);
    }

    private void setButtonStyles(Button button) {
        button.setStyle("-fx-background-color: #1e90ff; -fx-text-fill: white; -fx-font-size: 20px; ");
        button.setMinWidth(150);
    }

    private void setButtonHoverEffects(Button button) {
        button.setOnMouseEntered(e -> button.setScaleX(1.2));
        button.setOnMouseExited(e -> button.setScaleX(1));
    }

    public Button getPlayButton() {
        return playButton;
    }

    public Button getLeaderboardButton() {
        return leaderboardButton;
    }

    public Button getCloseButton() {
        return closeButton;
    }
}
