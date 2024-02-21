package Zeeslag.fx.Model;

import Zeeslag.fx.Manager.MVPModel;

public class LeaderBoardModel implements MVPModel {
    public LeaderBoardModel() {
        this.initialize();
        this.loadPresenters();
    }

    @Override
    public void initialize() {

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
