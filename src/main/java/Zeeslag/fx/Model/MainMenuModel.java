package Zeeslag.fx.Model;

import Zeeslag.fx.Manager.MVPModel;
import Zeeslag.fx.Manager.SceneUtil;
import Zeeslag.fx.View.Game.GamePresenter;
import Zeeslag.fx.View.Game.GameView;
import Zeeslag.fx.View.LeaderBoard.LeaderBoardPresenter;
import Zeeslag.fx.View.LeaderBoard.LeaderBoardView;

import Zeeslag.modulesVerzinBetereNaamXd.Game.GameManager;
import Zeeslag.modulesVerzinBetereNaamXd.Player.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainMenuModel implements MVPModel {
    public GameManager gameManager = GameManager.getInstance();
    public Button play;

    private GamePresenter gamePresenter;
    private LeaderBoardPresenter leaderBoardPresenter;

    public MainMenuModel() {
        initialize();
    }
    @FXML
    public void initialize(){
        gameManager.startGame();
        loadPresenters();
    }

    public void play() throws IOException {
        SceneUtil.openView(gamePresenter, "Game view");
    }

    public void directToLeaderBoard() throws IOException {
        SceneUtil.openView(leaderBoardPresenter, "Leaderboard");
    }

    @Override
    public void loadPresenters() {
        String RESET = "\u001B[0m";
        String GREEN = "\u001B[32m";
        String RED = "\u001B[31m";

        try {
            GameModel gameModel = new GameModel();
            GameView gameView = new GameView();
            gamePresenter = new GamePresenter(gameModel, gameView);

            LeaderBoardModel leaderBoardModel = new LeaderBoardModel();
            LeaderBoardView leaderBoardView = new LeaderBoardView();
            leaderBoardPresenter = new LeaderBoardPresenter(leaderBoardModel, leaderBoardView);

            System.out.println(GREEN + "Menu Presenters successfully loaded" + RESET);
        } catch (Exception e) {
            System.out.println(RED + "Menu Presenters failed to load" + RESET);
            e.printStackTrace();
            // Or handle the exception as appropriate for your application
        }
    }

}
