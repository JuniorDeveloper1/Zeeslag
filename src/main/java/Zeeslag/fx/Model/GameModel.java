package Zeeslag.fx.Model;

import Zeeslag.fx.Manager.MVPModel;
import Zeeslag.fx.Manager.SceneUtil;
import Zeeslag.fx.View.Game.player1.Player1Presenter;
import Zeeslag.fx.View.Game.player1.Player1View;
import Zeeslag.fx.View.Game.player2.Player2Presenter;
import Zeeslag.fx.View.Game.player2.Player2View;
import Zeeslag.modulesVerzinBetereNaamXd.Game.GameManager;
import Zeeslag.modulesVerzinBetereNaamXd.Player.Player;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GameModel implements MVPModel {
    private  GameManager gameManager = GameManager.getInstance();
    private Player1Presenter player1Presenter;
    private Player2Presenter player2Presenter;
    public GameModel() {
        this.initialize();
    }

    public void initialize(){

    }
    public void play(String player1, String player2) {
        createHero(player1, player2);

        /**
         * Presenters moeten hier geladen worden,
         * anders wordt de player niet geregistreerd in Player1/Player2 - Presenters
         */
        this.loadPresenters();

        String titlePlayer1 = gameManager.getPlayer1().getName() + "'s Battle";
        SceneUtil.openView(player1Presenter, titlePlayer1);

        String titlePlayer2 = gameManager.getPlayer2().getName() + "'s Battle";
        SceneUtil.openView(player2Presenter, titlePlayer2);


        System.out.println(gameManager.getPlayer1().getName());
        System.out.println(gameManager.getPlayer1().getUuid());

        System.out.println(gameManager.getPlayer2().getName());
        System.out.println(gameManager.getPlayer2().getUuid());
    }

    public void createHero(String sPlayer1, String sPlayer2) {
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

    public boolean checkIfTextFieldIsCorrect(TextField textField, TextField textField2, Label errorLabel){
        int minLength = 3;
        int maxLength = 15;

        if(textField.getText().isEmpty() || textField2.getText().isEmpty()) {
            errorLabel.setText("Both players must enter a name.");
            return false;
        }

        if(textField.getText().equals(textField2.getText())) {
            errorLabel.setText("Names must be different.");
            return false;
        }

        if(textField.getLength() < minLength || textField2.getLength() <  minLength ||
                textField.getLength() > maxLength || textField2.getLength() > maxLength) {
            errorLabel.setText("Names must be between 3 and 15 characters long.");
            return false;
        }


        errorLabel.setText("");
        return true;
    }

    @Override
    public void loadPresenters() {
        String RESET = "\u001B[0m";
        String GREEN = "\u001B[32m";
        String RED = "\u001B[31m";

        try {
            Player1Model player1Model = new Player1Model();
            Player1View player1View = new Player1View();
            player1Presenter = new Player1Presenter(player1Model, player1View);

            Player2Model player2Model = new Player2Model();
            Player2View player2View = new Player2View();
            player2Presenter = new Player2Presenter(player2Model, player2View);

            System.out.println(GREEN + " GamePresenters succesfully loaded " + RESET);
        }catch (NullPointerException e) {
            System.out.println(RED + "GamePresenters Failed to load" + RESET);
            throw new NullPointerException();
        }
    }
}
