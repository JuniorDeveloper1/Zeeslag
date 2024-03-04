package Zeeslag.Fx.View.Game.player2;

import Zeeslag.Fx.Manager.Presenter;
import Zeeslag.Fx.Manager.SceneUtil;
import Zeeslag.Fx.Model.Player2Model;
import javafx.scene.Node;

public class Player2Presenter implements Presenter {

    private Player2Model model;
    private Player2View view;

    public Player2Presenter(Player2Model model, Player2View view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        //this.addWindowEventHandler();
        this.updateView();
    }

    private void addEventHandlers() {

    }

    private void updateView() {
        String name = model.getGameManager().getPlayer2().getName() + "'s screen";;
        view.getPlayerName().setText(name);
    }

    public void addWindowEventHandler() {
        SceneUtil.closeSceneWarning(view.getScene());
    }

    public Player2Model getModel() {
        return model;
    }

    @Override
    public Node getView() {
        return view;
    }
}
