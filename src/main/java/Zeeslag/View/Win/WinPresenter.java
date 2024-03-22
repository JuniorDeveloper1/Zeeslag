package Zeeslag.View.Win;

import Zeeslag.Model.GameManager;
import Zeeslag.Model.helper.Presenter;
import Zeeslag.Model.helper.SceneUtil;
import Zeeslag.View.MainMenu.MainMenuPresenter;
import Zeeslag.View.MainMenu.MainMenuView;
import javafx.scene.Node;

public class WinPresenter implements Presenter {
    private WinView view;
    private String winnerName;
    private GameManager gameManager = GameManager.getInstance();

    public WinPresenter(WinView view) {
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getContinueGame().setOnMouseClicked(mouseEvent -> {
            MainMenuView mainMenuView = new MainMenuView();
            MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView);

            SceneUtil.closeScene(view.getScene());
            SceneUtil.openView(mainMenuPresenter);
        });

    }
    private void updateView() {
        if(gameManager.getPlayer1().hasWon(
                gameManager.getPlayer2(), view.getScene()
        )) {
            view.getPlayerName().setText(gameManager.getPlayer1().getName() + " has won");
            System.out.println(gameManager.getPlayer1().getName() + " has won");
        }else {
            view.getPlayerName().setText(gameManager.getPlayer2().getName() + " has won");
            System.out.println(gameManager.getPlayer2().getName() + " has won");
        }


    }
    @Override
    public Node getView() {
        return view;
    }
}
