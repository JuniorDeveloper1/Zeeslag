package Zeeslag.fx.View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainMenuView extends VBox {
    private Button playButton;
    private Button leaderboardButton;

    public MainMenuView () {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes(){
        // Initialize buttons
        playButton = new Button("Play");
        leaderboardButton = new Button("Leaderboard");

        // Set button styles
        playButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 20px;");
        leaderboardButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 20px;");

        // Set event handlers if needed

        // Create game name text
        Text gameName = new Text("Zeeslag");
        gameName.setFont(Font.font(30));

        // Add buttons and game name to an HBox
        HBox buttonsBox = new HBox(20, playButton, leaderboardButton);
        buttonsBox.setAlignment(Pos.CENTER_LEFT);

        // Add buttonsBox and game name to the layout
        getChildren().addAll(buttonsBox, gameName);
    }

    private void layoutNodes() {
        // Set alignment
        setAlignment(Pos.CENTER);

        // Set background color for the layout
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
    }

    public Button getPlayButton() {
        return playButton;
    }

    public Button getLeaderboardButton() {
        return leaderboardButton;
    }
}
