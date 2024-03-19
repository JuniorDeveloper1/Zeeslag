package Zeeslag.View.MainMenu;

import Zeeslag.Model.helper.MVPView;
import Zeeslag.Model.helper.SceneUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    private MediaPlayer mediaPlayer;
    private String audioFilePath;
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
      this.playBackgroundMusic();
    }

    @Override
    public void layoutNodes() {
        zeeslagText.setFont(Font.font("Arial", 60));
        zeeslagText.setFill(Color.WHITE);

        SceneUtil.setButtonStyles(playButton);
        SceneUtil.setButtonStyles(leaderboardButton);
        SceneUtil.setButtonStyles(closeButton);

        SceneUtil.setButtonHoverEffects(playButton);
        SceneUtil.setButtonHoverEffects(leaderboardButton);
        SceneUtil.setButtonHoverEffects(closeButton);

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

    private void playBackgroundMusic() {
        audioFilePath = "resources/awesomeness.wav";
        File file = new File(audioFilePath);
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
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

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
