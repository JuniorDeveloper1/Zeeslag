package Zeeslag.fx.View;

import Zeeslag.fx.View.ViewInterface.MVPView;
import com.almasb.fxgl.core.View;
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
        System.out.println("Game view");
    }
    @Override
    public void initialize() {
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        this.setPrefWidth(screenSize.getWidth());
        this.setPrefHeight(screenSize.getHeight());
    }
    @Override
    public void initializeNodes(){
        System.out.println("Has laoded");
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
