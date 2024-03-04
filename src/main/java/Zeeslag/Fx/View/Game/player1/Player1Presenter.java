package Zeeslag.fx.View.Game.player1;

import Zeeslag.fx.Manager.Presenter;
import Zeeslag.fx.Model.GameModel;
import Zeeslag.fx.Model.Player1Model;
import Zeeslag.fx.View.Game.GameView;
import javafx.scene.Node;

public class Player1Presenter implements Presenter {

    private Player1Model model;
    private Player1View view;
    public Player1Presenter(Player1Model model, Player1View view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {

    }

    private void updateView() {
       String name = model.getGameManager().getPlayer1().getName() + "'s screen";
       view.getPlayerName().setText(name);

    }

    public Player1Model getModel() {
        return model;
    }

    @Override
    public Node getView() {
        return view;
    }
}
