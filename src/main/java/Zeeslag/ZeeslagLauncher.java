package Zeeslag;

import Zeeslag.Fx.Model.MainMenuModel;
import Zeeslag.Fx.View.MainMenu.MainMenuPresenter;
import Zeeslag.Fx.View.MainMenu.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ZeeslagLauncher extends Application {
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
