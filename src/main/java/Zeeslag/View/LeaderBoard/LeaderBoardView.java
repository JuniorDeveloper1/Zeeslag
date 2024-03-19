package Zeeslag.View.LeaderBoard;

import Zeeslag.Model.Core.MusicPlayer;
import Zeeslag.Model.GameManager;
import Zeeslag.Model.helper.MVPView;
import Zeeslag.Model.helper.SceneUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;

public class LeaderBoardView extends VBox implements MVPView {
    private VBox titleBox;
    private Text title;

    private Label[] leaderboardLabel;

    private Button returnButton;

    private GameManager gameManager = GameManager.getInstance();


    public LeaderBoardView()  {
        this.initialize();
        this.initializeNodes();
        this.layoutNodes();


    }
    @Override
    public void initialize() {
        this.setPrefWidth(800);
        this.setPrefHeight(600);
    }

    @Override
    public void initializeNodes() {
        this.titleBox = new VBox();
        this.title = new Text("leaderboard");
        this.leaderboardLabel = new Label[10]; //10 places
        for (int i = 0; i < leaderboardLabel.length; i++) {
            leaderboardLabel[i] = new Label();
        }

        this.returnButton = new Button("return");

    }

    @Override
    public void layoutNodes() {
        title.setFont(Font.font("Arial", 30));
        titleBox.setPadding(new Insets(0, 0, 100, 0));

        titleBox.setAlignment(Pos.CENTER);
        setAlignment(Pos.TOP_CENTER);
        setSpacing(5);

        this.getChildren().add(titleBox);
        titleBox.getChildren().add(title);

        for (int i = 0; i < leaderboardLabel.length; i++) {
            Label labels = getLeaderboardLabel()[i];
            labels.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            labels.setPadding(new Insets(0,0,10,0));


            switch (i) {
                case 0:
                    setColorLabel(i, "#FFD700");
                    break;
                case 1:
                    setColorLabel(i, "#C0C0C0");
                    break;
                case 2:
                    setColorLabel(i, "#cd7f32");
                    break;
            }

            this.getChildren().addAll(labels);
        }
        SceneUtil.setButtonHoverEffects(returnButton);
        SceneUtil.setButtonStyles(returnButton);

        this.getChildren().add(returnButton);


    }

    private void setColorLabel(int index, String color){
        if(index >= 0 && index < getLeaderboardLabel().length) {
            getLeaderboardLabel()[index].setStyle("-fx-text-fill: " + color +";");
        }
    }
    public Text getTitle() {
        return title;
    }

    public Label[] getLeaderboardLabel() {
        return leaderboardLabel;
    }

    public Button getReturnButton() {
        return returnButton;
    }

    public MusicPlayer getMusicPlayer() {
        return gameManager.getMusicPlayer();
    }
}
