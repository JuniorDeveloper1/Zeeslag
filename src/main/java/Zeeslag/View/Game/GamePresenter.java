package Zeeslag.View.Game;

import Zeeslag.Model.GameManager;
import Zeeslag.Model.PlayerManager;
import Zeeslag.Model.helper.Presenter;
import Zeeslag.Model.helper.SceneUtil;
import Zeeslag.View.Player1.PlayerPresenter;
import Zeeslag.View.Player1.PlayerView;
import Zeeslag.View.Player2.Player2Presenter;
import Zeeslag.View.Player2.Player2View;
import Zeeslag.View.MainMenu.MainMenuPresenter;
import Zeeslag.View.MainMenu.MainMenuView;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class GamePresenter implements Presenter {
    private GameManager model;
    private GameView view;

    private GameManager gameManager = GameManager.getInstance();

    public GamePresenter(GameManager model, GameView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getPlayButton().setOnMouseClicked(mouseEvent -> {
            if (model.validateTextFieldInput(view.getPlayName1Field(),
                    view.getPlayName2Field())) {
                String player1Name = view.getPlayName1Field().getText();
                String player2Name = view.getPlayName2Field().getText();

                try {
                    model.playGame(player1Name, player2Name, view.getBoardSizes());
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                PlayerManager playerManager = new PlayerManager(player1Name, player2Name);
                PlayerView player1View = new PlayerView();
                PlayerPresenter player1Presenter = new PlayerPresenter(playerManager, player1View);

                if(gameManager.isPlayingAgainstBot()){
                    SceneUtil.openView(player1Presenter);
                }else {
                    Player2View player2View = new Player2View();
                    Player2Presenter player2Presenter = new Player2Presenter(playerManager, player2View);

                    SplitPane splitPane = new SplitPane(player1View, player2View);
                    splitPane.setDividerPositions(0.5);

                    Stage stage = new Stage();
                    stage.setScene(new Scene(splitPane));
                    stage.show();
                }

                SceneUtil.closeScene(view.getScene());
            }
        });

        view.getReturnButton().setOnMouseClicked(mouseEvent -> {
            SceneUtil.closeScene(view.getScene());

            MainMenuView mainMenuView = new MainMenuView();
            MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView);

            SceneUtil.openView(mainMenuPresenter);
        });
    }

    private void updateView() {
        // Add logic to update view if needed
    }


    @Override
    public Node getView() {
        return view;
    }
}
