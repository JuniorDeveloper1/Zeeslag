package Zeeslag.fx.Model;

import Zeeslag.fx.Manager.MVPModel;
import Zeeslag.modulesVerzinBetereNaamXd.Game.GameManager;

public class GameModel implements MVPModel {
    GameManager gameManager = GameManager.getInstance();
    public GameModel() {
        this.initialize();
    }

    public void initialize(){

    }

    public void test(){

        System.out.println(gameManager.getPlayer1().getName());
        System.out.println(gameManager.getPlayer1().getUuid());

        System.out.println(gameManager.getPlayer2().getName());
        System.out.println(gameManager.getPlayer2().getUuid());

    }

    @Override
    public void loadPresenters() {
        String RESET = "\u001B[0m";
        String GREEN = "\u001B[32m";
        String RED = "\u001B[31m";

        try {
            /** TODO: Load all presenter classes here! **/

            System.out.println(GREEN + " Presenters succesfully loaded " + RESET);
        }catch (NullPointerException e) {
            System.out.println(RED + "Presenters Failed to load" + RESET);
            throw new NullPointerException();
        }
    }
}
