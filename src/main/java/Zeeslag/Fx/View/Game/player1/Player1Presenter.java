package Zeeslag.Fx.View.Game.player1;

import Zeeslag.Fx.Manager.Presenter;
import Zeeslag.Fx.Manager.SceneUtil;
import Zeeslag.Fx.Model.Player1Model;
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

    public void addWindowEventHandler() {
        SceneUtil.closeSceneWarning(view.getScene());
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
