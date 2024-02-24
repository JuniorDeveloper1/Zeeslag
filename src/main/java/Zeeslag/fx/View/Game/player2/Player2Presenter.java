package Zeeslag.fx.View.Game.player2;

import Zeeslag.fx.Manager.Presenter;
import Zeeslag.fx.Model.GameModel;
import Zeeslag.fx.Model.Player2Model;
import Zeeslag.fx.View.Game.GameView;
import javafx.scene.Node;

public class Player2Presenter implements Presenter {

    private Player2Model model;
    private Player2View view;

    public Player2Presenter(Player2Model model, Player2View view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {

    }

    private void updateView() {
        String name = model.getGameManager().getPlayer2().getName() + "'s screen";;
        view.getPlayerName().setText(name);
    }

    public Player2Model getModel() {
        return model;
    }

    @Override
    public Node getView() {
        return view;
    }
}
