package Zeeslag.View.LeaderBoard;

import Zeeslag.Model.Core.Leaderboard;
import Zeeslag.Model.helper.Presenter;
import Zeeslag.Model.Core.PlayerStats;
import javafx.scene.Node;

import java.util.List;

public class LeaderBoardPresenter implements Presenter {

    private Leaderboard model;
    private LeaderBoardView view;

    public LeaderBoardPresenter(Leaderboard model, LeaderBoardView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {

    }


    private void updateView() {
        this.updateLeaderboard(model.getPlayerStats());
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


    public Leaderboard getModel() {
        return model;
    }

    public Node getView() {
        return view;
    }
}
