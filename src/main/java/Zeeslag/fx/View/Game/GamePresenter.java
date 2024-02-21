package Zeeslag.fx.View.Game;

import Zeeslag.fx.Model.GameModel;
import Zeeslag.fx.Manager.Presenter;
import javafx.scene.Node;

public class GamePresenter implements Presenter {
    private GameModel model;
    private GameView view;

    public GamePresenter(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }


    private void addEventHandlers() {
        view.getTestButton().setOnAction(event -> model.test());
    }

    private void updateView() {

    }


    @Override
    public Node getView() {
        return view;
    }
}
