package Zeeslag.Fx.View.Game;

import Zeeslag.Fx.Manager.SceneUtil;
import Zeeslag.Fx.Model.GameModel;
import Zeeslag.Fx.Manager.Presenter;
import Zeeslag.Fx.Model.Player1Model;
import Zeeslag.Fx.Model.Player2Model;
import Zeeslag.Fx.View.Game.player1.Player1Presenter;
import Zeeslag.Fx.View.Game.player1.Player1View;
import Zeeslag.Fx.View.Game.player2.Player2Presenter;
import Zeeslag.Fx.View.Game.player2.Player2View;
import javafx.scene.Node;
import javafx.stage.Stage;

public class GamePresenter implements Presenter {
    private GameModel model;
    private GameView view;
    private Stage player1Stage;
    private Stage player2Stage;

    public GamePresenter(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getPlayButton().setOnMouseClicked(mouseEvent -> {
            if (model.checkIfTextFieldIsCorrect(view.getPlayName1Field(),
                    view.getPlayName2Field())) {
                view.stopBackgroundMusic();

                String player1Name = view.getPlayName1Field().getText();
                String player2Name = view.getPlayName2Field().getText();

                model.play(player1Name, player2Name);

                Player2Model player2Model = new Player2Model();
                Player2View player2View = new Player2View();
                Player2Presenter player2Presenter = new Player2Presenter(player2Model, player2View);
                SceneUtil.openView(player2Presenter);
                player2Presenter.addWindowEventHandler();

                Player1Model player1Model = new Player1Model();
                Player1View player1View = new Player1View();
                Player1Presenter player1Presenter = new Player1Presenter(player1Model, player1View);
                SceneUtil.openView(player1Presenter);
                player1Presenter.addWindowEventHandler();

                handleClose();
            }
        });
    }

    private void updateView() {
        // Add logic to update view if needed
    }

    private void handleClose() {
        view.getScene().getWindow().hide();
    }

    @Override
    public Node getView() {
        return view;
    }
    
}
