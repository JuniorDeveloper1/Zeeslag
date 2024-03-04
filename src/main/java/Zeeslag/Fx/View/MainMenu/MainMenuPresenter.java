package Zeeslag.Fx.View.MainMenu;

import Zeeslag.Fx.Manager.SceneUtil;
import Zeeslag.Fx.Model.GameModel;
import Zeeslag.Fx.Model.LeaderBoardModel;
import Zeeslag.Fx.Model.MainMenuModel;
import Zeeslag.Fx.Manager.Presenter;
import Zeeslag.Fx.View.Game.GamePresenter;
import Zeeslag.Fx.View.Game.GameView;
import Zeeslag.Fx.View.LeaderBoard.LeaderBoardPresenter;
import Zeeslag.Fx.View.LeaderBoard.LeaderBoardView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

import java.io.FileNotFoundException;

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
        //SceneUtil.openView(gamePresenter, "Game view");
        view.getPlayButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.stopBackgroundMusic();
                GameView gameView = new GameView();
                GameModel gameModel = new GameModel();
                GamePresenter gamePresenter = new GamePresenter(gameModel, gameView);
                SceneUtil.openView(gamePresenter);
            }
        });

        view.getLeaderboardButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.stopBackgroundMusic();
                LeaderBoardView leaderBoardView = new LeaderBoardView();
                LeaderBoardModel leaderBoardModel;
                try {
                     leaderBoardModel = new LeaderBoardModel();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                LeaderBoardPresenter leaderBoardPresenter = new LeaderBoardPresenter(leaderBoardModel, leaderBoardView);
                SceneUtil.openView(leaderBoardPresenter);
            }
        });
        view.getCloseButton().setOnAction(event -> handleClose());
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
