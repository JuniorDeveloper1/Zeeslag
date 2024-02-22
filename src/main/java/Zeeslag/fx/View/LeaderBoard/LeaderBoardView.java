package Zeeslag.fx.View.LeaderBoard;

import Zeeslag.fx.Manager.MVPView;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class LeaderBoardView extends VBox implements MVPView {
    private Button loadScoreBoardToConsole;

    public LeaderBoardView() {
        this.initialize();
        this.initializeNodes();
        this.layoutNodes();
    }
    @Override
    public void initialize() {
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        this.setPrefWidth(screenSize.getWidth());
        this.setPrefHeight(screenSize.getHeight());
    }

    @Override
    public void initializeNodes() {
        this.loadScoreBoardToConsole = new Button("console");

    }

    @Override
    public void layoutNodes() {
        this.getChildren().add(loadScoreBoardToConsole);
    }

    public Button getLoadScoreBoardToConsole() {
        return loadScoreBoardToConsole;
    }
}
