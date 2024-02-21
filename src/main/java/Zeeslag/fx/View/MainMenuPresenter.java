package Zeeslag.fx.View;

import Zeeslag.fx.Model.MainMenuModel;

public class MainMenuPresenter {

    private MainMenuModel model;
    private MainMenuView view;

    public MainMenuPresenter(MainMenuModel model, MainMenuView view) {
        this.model = model;
        this.view = view;
    }

    private void addEventHandlers() {

    }

    private void updateView() {

    }

    public MainMenuModel getModel() {
        return model;
    }

    public MainMenuView getView() {
        return view;
    }
}
