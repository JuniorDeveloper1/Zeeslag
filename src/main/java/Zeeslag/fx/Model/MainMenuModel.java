package Zeeslag.fx.Model;

import Zeeslag.fx.Manager.MVPModel;
import Zeeslag.fx.Manager.SceneUtil;
import Zeeslag.fx.View.Game.GamePresenter;
import Zeeslag.fx.View.Game.GameView;
import Zeeslag.fx.View.LeaderBoard.LeaderBoardView;
import Zeeslag.fx.View.MainMenu.LeaderBoardPresenter;
import Zeeslag.modulesVerzinBetereNaamXd.Game.GameManager;
import Zeeslag.modulesVerzinBetereNaamXd.Player.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
        /**
         * Logic for creating names for createHero(String, String);
         */
        SceneUtil.openView(gamePresenter, "Game view");
        createHero(null, null);
    }

    public void directToLeaderBoard() throws IOException {
        SceneUtil.openView(leaderBoardPresenter, "Leaderboard");
    }



    private void createHero(String sPlayer1, String sPlayer2) {
        if(sPlayer1 == null) {
            sPlayer1 = "Bot1";
        }

        if(sPlayer2 == null) {
            sPlayer2 = "Bot2";
        }

        Player player1 = new Player(sPlayer1, gameManager.getPlayer1().getBoard());
        Player player2 = new Player(sPlayer2, gameManager.getPlayer2().getBoard());

        gameManager.setPlayer1(player1);
        gameManager.setPlayer2(player2);
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
            leaderBoardPresenter
                    = new LeaderBoardPresenter(leaderBoardModel, leaderBoardView);


            System.out.println(GREEN + " Presenters succesfully loaded " + RESET);

        }catch (NullPointerException e) {
            System.out.println(RED + "Presenters Failed to load" + RESET);
            throw new NullPointerException();
        }

    }
}
