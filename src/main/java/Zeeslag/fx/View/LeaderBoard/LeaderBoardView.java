package Zeeslag.fx.View.LeaderBoard;

import Zeeslag.fx.Manager.MVPView;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class LeaderBoardView extends VBox implements MVPView {

    public LeaderBoardView() {
        this.initialize();
    }
    @Override
    public void initialize() {
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        this.setPrefWidth(screenSize.getWidth());
        this.setPrefHeight(screenSize.getHeight());
    }

    @Override
    public void initializeNodes() {

    }

    @Override
    public void layoutNodes() {

    }
}
