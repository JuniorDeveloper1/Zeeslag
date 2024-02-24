package Zeeslag.fx.View.MainMenu;

import Zeeslag.fx.Model.MainMenuModel;
import Zeeslag.fx.Manager.Presenter;
import javafx.scene.Node;

import java.io.IOException;

public class MainMenuPresenter implements Presenter {

    private MainMenuModel model;
    private MainMenuView view;
    private MainMenuPresenter presenter;

    public MainMenuPresenter(MainMenuModel model, MainMenuView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        // Koppelt event handlers (anon. inner klassen)
        // aan de controls uit de view.
        // Event handlers: roepen methodes aan uit het
        // model en zorgen voor een update van de view.

        view.getPlayButton().setOnAction(event -> {
            try {
                model.play();
            }  catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        view.getLeaderboardButton().setOnAction(event -> {
            try {
                model.directToLeaderBoard();
            }catch (IOException e){
                throw  new RuntimeException(e);
            }
        });

        view.getCloseButton().setOnMouseClicked(event -> handleClose());
    }

    private void updateView() {
        //view.getStatusLabel().setText(model.getStatusText());

    }

    public void addWindowEventHandlers() {
        // Window event handlers (anon. inner klassen)
        // Koppeling via view.getScene().getWindow()
        view.getPlayButton().setOnMouseClicked(event -> handleClose());
        view.getLeaderboardButton().setOnMouseClicked(event -> handleClose());
    }

    private void handleClose() {
        view.getScene().getWindow().hide();
    }


    public MainMenuModel getModel() {
        return model;
    }

    public Node getView() {
        return view;
    }
}
