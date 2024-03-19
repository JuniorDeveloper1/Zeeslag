package Zeeslag;

import Zeeslag.Model.GameManager;
import Zeeslag.View.MainMenu.MainMenuPresenter;
import Zeeslag.View.MainMenu.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ZeeslagLauncher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GameManager.getInstance().startMusic();
        MainMenuView view = new MainMenuView();
        MainMenuPresenter presenter = new MainMenuPresenter(view);
        stage.setScene(new Scene(view));
        presenter.addWindowEventHandlers();
        stage.setTitle("Zeeslag");
        stage.show();

    }
}
