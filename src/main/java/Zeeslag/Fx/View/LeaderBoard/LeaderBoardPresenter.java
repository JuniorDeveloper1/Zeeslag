package Zeeslag.Fx.View.LeaderBoard;

import Zeeslag.Fx.Model.LeaderBoardModel;
import Zeeslag.Fx.Manager.Presenter;
import Zeeslag.Core.Player.PlayerStats;
import javafx.scene.Node;

import java.util.List;

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

    }


    private void updateView() {
        //view.getStatusLabel().setText(model.getStatusText());
        this.updateLeaderboard(model.getLeaderboard().getPlayerStats());
    }

    public void addWindowEventHandlers() {

    }

    private void updateLeaderboard(List<PlayerStats> playerStatsList) {
        String trophyEmoji = "\uD83C\uDFC6";
        for (int i = 0; i < view.getLeaderboardLabel().length && i < playerStatsList.size(); i++) {
            PlayerStats stats = playerStatsList.get(i);
            if(i == 0) {
                view.getLeaderboardLabel()[i].setText((1+i) + ". "
                        + stats.getPlayerName() + " - Wins: " + stats.getWins() + trophyEmoji);
            }else {
                view.getLeaderboardLabel()[i].setText((1+i) + ". "
                        + stats.getPlayerName() + " - Wins: " + stats.getWins());
            }

        }
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
