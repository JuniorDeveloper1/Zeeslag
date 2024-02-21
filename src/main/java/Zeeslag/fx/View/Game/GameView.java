package Zeeslag.fx.View.Game;

import Zeeslag.fx.Manager.MVPView;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class GameView extends VBox implements MVPView {
    private Button testButton;
    public GameView () {
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
    public void initializeNodes(){
        testButton = new Button("Test");
        // Example
        // Button button = new Button()...
    }
    @Override
    public void layoutNodes() {
        this.getChildren().add(getTestButton());
        //CSS
    }

    public Button getTestButton() {
        return testButton;
    }
}
