package Zeeslag.fx.Model;

import Zeeslag.fx.Manager.MVPModel;
import Zeeslag.modulesVerzinBetereNaamXd.Game.GameManager;

import java.io.IOException;

public class Player1Model implements MVPModel {
    private final GameManager gameManager = GameManager.getInstance();
    @Override
    public void initialize() {

    }

    @Override
    public void loadPresenters() {

    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
