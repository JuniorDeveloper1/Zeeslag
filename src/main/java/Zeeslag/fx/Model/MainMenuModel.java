package Zeeslag.fx.Model;

import Zeeslag.fx.Manager.LoadPresenter;
import Zeeslag.fx.Manager.SceneUtil;
import Zeeslag.fx.View.GamePresenter;
import Zeeslag.fx.View.GameView;
import Zeeslag.modulesVerzinBetereNaamXd.Game.GameManager;
import Zeeslag.modulesVerzinBetereNaamXd.Player.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuModel implements LoadPresenter {
    public GameManager gameManager = GameManager.getInstance();
    public Button play;

    public GamePresenter presenter;

    @FXML
    public void initialize(){
        gameManager.startGame();

        loadPresenters();
    }

    public void play() throws IOException {
        /**
         * Logic for creating names for createHero(String, String);
         */

        createHero(null, null);

        //System.out.println(gameManager.getPlayer1().getName());
        //System.out.println(gameManager.getPlayer1().getUuid());
        SceneUtil.openScene("game_controller.fxml", "game", presenter.getModel()); //Game controller?
        SceneUtil.closeScene(play);
        //gameManager.getGame().loadDefault();
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
        GameModel model = new GameModel();
        GameView view = new GameView();
        presenter = new GamePresenter(model, view);
    }
}
