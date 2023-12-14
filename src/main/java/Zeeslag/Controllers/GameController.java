package Zeeslag.Controllers;

import Zeeslag.GameManager;

public class GameController {
    GameManager gameManager = GameManager.getInstance();

    public void initialize(){

    }

    public void play(){
        System.out.println(gameManager.getPlayer1().getName());
        System.out.println(gameManager.getPlayer1().getUuid());
    }
}
