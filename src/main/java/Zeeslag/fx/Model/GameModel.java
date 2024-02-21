package Zeeslag.fx.Model;

import Zeeslag.modulesVerzinBetereNaamXd.Game.GameManager;

public class GameModel {
    GameManager gameManager = GameManager.getInstance();

    public void initialize(){

    }

    public void play(){
        System.out.println(gameManager.getPlayer1().getName());
        System.out.println(gameManager.getPlayer1().getUuid());

    }
}
