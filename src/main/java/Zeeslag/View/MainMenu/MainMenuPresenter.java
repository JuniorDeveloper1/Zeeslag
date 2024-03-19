package Zeeslag.View.MainMenu;

import Zeeslag.Model.GameManager;
import Zeeslag.Model.Core.Leaderboard;
import Zeeslag.Model.helper.SceneUtil;
import Zeeslag.Model.helper.Presenter;
import Zeeslag.View.Game.GamePresenter;
import Zeeslag.View.Game.GameView;
import Zeeslag.View.LeaderBoard.LeaderBoardPresenter;
import Zeeslag.View.LeaderBoard.LeaderBoardView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;

import java.io.FileNotFoundException;

public class MainMenuPresenter implements Presenter {

   // private MainMenuModel model;
    private MainMenuView view;
    private GameManager gameManager = GameManager.getInstance();

    public MainMenuPresenter(MainMenuView view) {
       // this.model = model;
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
        view.getPlayButton().setOnAction(actionEvent -> {
            SceneUtil.closeScene(view.getScene());
            GameView gameView = new GameView();
            GamePresenter gamePresenter = new GamePresenter(gameManager, gameView);
            SceneUtil.openView(gamePresenter);
        });

        view.getLeaderboardButton().setOnAction(actionEvent -> {
            SceneUtil.closeScene(view.getScene());
            LeaderBoardView leaderBoardView = new LeaderBoardView();
            Leaderboard leaderBoardModel;
            try {
                leaderBoardModel = new Leaderboard();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            LeaderBoardPresenter leaderBoardPresenter = new LeaderBoardPresenter(leaderBoardModel, leaderBoardView);
            SceneUtil.openView(leaderBoardPresenter);
        });
        view.getCloseButton().setOnAction(event -> SceneUtil.closeScene(view.getScene()));
    }

    private void updateView() {
        //view.getStatusLabel().setText(model.getStatusText());
    }

    public void addWindowEventHandlers() {
        // Window event handlers (anon. inner klassen)
        // Koppeling via view.getScene().getWindow()
        view.getPlayButton().setOnMouseClicked(event -> SceneUtil.closeScene(view.getScene()));
        view.getLeaderboardButton().setOnMouseClicked(event -> SceneUtil.closeScene(view.getScene()));
    }


    public Node getView() {
        return view;
    }
}
