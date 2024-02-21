package Zeeslag;

import Zeeslag.fx.Model.MainMenuModel;
import Zeeslag.fx.View.MainMenu.MainMenuPresenter;
import Zeeslag.fx.View.MainMenu.MainMenuView;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Zeeslag extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        MainMenuView view = new MainMenuView();
        MainMenuModel model = new MainMenuModel();
        MainMenuPresenter presenter = new MainMenuPresenter(model, view);
        stage.setScene(new Scene(view));
        presenter.addWindowEventHandlers();
        stage.show();

    }
}
