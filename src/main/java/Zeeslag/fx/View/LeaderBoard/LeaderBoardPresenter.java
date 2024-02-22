package Zeeslag.fx.View.MainMenu;

import Zeeslag.fx.Model.LeaderBoardModel;
import Zeeslag.fx.View.LeaderBoard.LeaderBoardView;
import Zeeslag.fx.Manager.Presenter;
import javafx.scene.Node;

public class LeaderBoardPresenter implements Presenter {

    private LeaderBoardModel model;
    private LeaderBoardView view;

    public LeaderBoardPresenter(LeaderBoardModel model, LeaderBoardView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getLoadScoreBoardToConsole().setOnMouseClicked(mouseEvent -> model.showLeaderboardToConsole());
    }


    private void updateView() {
        //view.getStatusLabel().setText(model.getStatusText());

    }

    public void addWindowEventHandlers() {

    }

    private void handleClose() {
        view.getScene().getWindow().hide();
    }


    public LeaderBoardModel getModel() {
        return model;
    }

    public Node getView() {
        return view;
    }
}
