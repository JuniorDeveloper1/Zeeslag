package Zeeslag;

import Zeeslag.fx.Model.MainMenuModel;
import Zeeslag.fx.View.MainMenuPresenter;
import Zeeslag.fx.View.MainMenuView;
import Zeeslag.modulesVerzinBetereNaamXd.Game.GameManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Zeeslag extends Application {
    GameManager gameManager = GameManager.getInstance();
    @Override
    public void start(Stage stage) throws Exception {
        MainMenuModel model = new MainMenuModel();
        MainMenuView view = new MainMenuView();
         new MainMenuPresenter(model, view);

        stage.setScene(new Scene(view));
        stage.show();

    }
}