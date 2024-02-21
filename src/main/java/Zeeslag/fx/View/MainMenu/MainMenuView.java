package Zeeslag.fx.View.MainMenu;

import Zeeslag.fx.Manager.MVPView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class MainMenuView extends VBox implements MVPView {
    private Button playButton;
    private Button leaderboardButton;
    private Button closeButton;
    private  VBox zeeslagTextBox;
    private Text zeeslagText;

    public MainMenuView() {
        initialize();
        initializeNodes();
        layoutNodes();
    }

    @Override
    public void initialize() {
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        this.setPrefWidth(screenSize.getWidth());
        this.setPrefHeight(screenSize.getHeight());
    }

    @Override
    public void initializeNodes() {
        // Initialize buttons
        playButton = new Button("Play");
        leaderboardButton = new Button("Leaderboard");
        closeButton = new Button("Close");

        zeeslagTextBox= new VBox();
        zeeslagText = new Text("Zeeslag");


    }

    private void setButtonStyles(Button button) {
        button.setStyle("-fx-background-color: #1e90ff; -fx-text-fill: white; -fx-font-size: 20px;");
        button.setMinWidth(150); // Set button width
    }

    private void setButtonHoverEffects(Button button) {
        button.setOnMouseEntered(e -> button.setScaleX(1.2)); // Increase scale on hover
        button.setOnMouseExited(e -> button.setScaleX(1)); // Restore scale on exit
    }

    @Override
    public void layoutNodes() {
        // Set button styles
        setButtonStyles(playButton);
        setButtonStyles(leaderboardButton);
        setButtonStyles(closeButton);

        // Set hover effects
        setButtonHoverEffects(playButton);
        setButtonHoverEffects(leaderboardButton);
        setButtonHoverEffects(closeButton);

        // Set alignment
        setAlignment(Pos.CENTER_LEFT);

        // Set spacing between nodes
        setSpacing(20);

        // Set background color
        setBackground(new Background(new BackgroundFill(Color.rgb(0, 128, 128), CornerRadii.EMPTY, Insets.EMPTY)));

        // Add buttons to the layout
        getChildren().addAll(playButton, leaderboardButton, closeButton);

        // Set the text to be centered right

        zeeslagTextBox.setAlignment(Pos.CENTER_RIGHT);

        zeeslagText.setFont(Font.font("Arial", 60)); // Increase font size
        zeeslagText.setFill(Color.WHITE); // Set text color
        zeeslagTextBox.getChildren().addAll(zeeslagText);
        getChildren().add(zeeslagTextBox);
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
