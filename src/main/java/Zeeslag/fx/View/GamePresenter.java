package Zeeslag.fx.View;

import Zeeslag.fx.Model.GameModel;
import Zeeslag.fx.Model.MainMenuModel;

public class GamePresenter {
    private GameModel model;
    private GameView view;

    public GamePresenter(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }


    private void addEventHandlers() {

    }

    private void updateView() {

    }

    public GameModel getModel() {
        return model;
    }

    public GameView getView() {
        return view;
    }

}
