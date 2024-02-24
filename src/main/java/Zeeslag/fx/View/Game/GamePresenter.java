package Zeeslag.fx.View.Game;

import Zeeslag.fx.Model.GameModel;
import Zeeslag.fx.Manager.Presenter;
import javafx.scene.Node;

import java.util.concurrent.atomic.AtomicReference;

public class GamePresenter implements Presenter {
    private GameModel model;
    private GameView view;

    public GamePresenter(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }


    private void addEventHandlers() {
        AtomicReference<String> player1 = new AtomicReference<>();
        AtomicReference<String> player2 = new AtomicReference<>();


        view.getPlayButton().setOnMouseClicked(mouseEvent -> {
            if (model.checkIfTextFieldIsCorrect(view.getPlayName1Field()
                    , view.getPlayName2Field()
                    , view.getErrorLabel())) {
                player1.set(view.getPlayName1Field().getText());
                player2.set(view.getPlayName2Field().getText());

                model.play(player1.get(), player2.get());

                handleClose();
            }
        });

    }

    private void updateView() {

    }

    private void handleClose() {
        view.getScene().getWindow().hide();
    }


    @Override
    public Node getView() {
        return view;
    }
}
