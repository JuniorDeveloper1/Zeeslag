package Zeeslag.View.Player1;

import Zeeslag.Model.Core.NPC;
import Zeeslag.Model.Core.Player;
import Zeeslag.Model.GameManager;
import Zeeslag.Model.PlayerManager;
import Zeeslag.Model.helper.Presenter;
import Zeeslag.Model.helper.SceneUtil;
import Zeeslag.View.Win.WinPresenter;
import Zeeslag.View.Win.WinView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class PlayerPresenter implements Presenter {
    private final PlayerManager model;
    private final PlayerView view;

    private final Player currentPlayer;
    private final Player bot;
    private final GameManager gameManager = GameManager.getInstance();
    private Timeline botAttackTimer;

    public PlayerPresenter(PlayerManager model, PlayerView view) {
        this.model = model;
        this.view = view;
        initializeEventHandlers();
        this.currentPlayer = model.getGameManager().getPlayer1();
        this.bot = gameManager.getBot();
        this.updateView();
        view.getOpponentGridPane().setVisible(false);
        view.getWaitingForOtherPlayer().setVisible(false);
    }

    private void initializeEventHandlers() {
        initializeGridPaneClickHandler();
        initializeStartButtonClickHandler();
    }

    private void updateView() {
        String playerName = model.getGameManager().getPlayer1().getName();
        view.getPlayerName().setText(playerName + "'s board.");
    }

    private void initializeGridPaneClickHandler() {
        GridPane gridPane = view.getGridPane();
        gridPane.setOnMouseClicked(this::handleGridPaneClick);

        GridPane opponentBoard = view.getOpponentGridPane();
        opponentBoard.setOnMouseClicked(mouseEvent -> {
            if (model.getGameManager().isPlayerReady()) {
                Platform.runLater(() -> {
                    handleAttack(mouseEvent);
                });

            }
        });
    }

    private void handleGridPaneClick(MouseEvent mouseEvent) {
        int size = model.getGameManager().getPlayer1().getBoard().getSizeBoard();
        double cellWidth = view.getGridPane().getWidth() / size;
        double cellHeight = view.getGridPane().getHeight() / size;
        MouseButton button = mouseEvent.getButton();
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();
        int x = (int) (mouseX / cellWidth);
        int y = (int) (mouseY / cellHeight);

        if (button == MouseButton.PRIMARY && !model.getGameManager().hasStarted()) {
            handlePrimaryClick(x, y);
        } else if (button == MouseButton.SECONDARY && !model.getGameManager().hasStarted()) {
            handleSecondaryClick(x, y);
        }
    }

    private void handlePrimaryClick(int x, int y) {
        if (getCurrentPlayer().getBoard().isValidPoint(x, y)) {
            boolean successfulPlaced = model.placeShipHorizontal(getCurrentPlayer(), x, y);
            if (getCurrentPlayer().getBoard().isAllShipsPlaced()) {
                SceneUtil.showAlert("Ships placed", "All ships are placed");
            } else if (!successfulPlaced) {
                SceneUtil.showAlert("Ship misplaced", "You cannot add a ship there!");
            }
        } else {
            SceneUtil.showAlert("Ship misplaced", "You cannot add a ship there!");
        }
    }

    private void handleSecondaryClick(int x, int y) {
        if (getCurrentPlayer().getBoard().isValidPoint(x, y)) {
            boolean successfulPlaced = model.placeShipVertical(getCurrentPlayer(), x, y);
            if (getCurrentPlayer().getBoard().isAllShipsPlaced()) {
                SceneUtil.showAlert("Ships placed", "All ships are placed");
            } else if (!successfulPlaced) {
                SceneUtil.showAlert("Ship misplaced", "You cannot add a ship there!");
            }
        } else {
            SceneUtil.showAlert("Ship misplaced", "You cannot add a ship there!");
        }
    }
    private void initializeStartButtonClickHandler() {
        Button startButton = view.getStart();
        startButton.setOnMouseClicked(mouseEvent -> {
            handleStartButtonClick();
        });
    }

    private void handleStartButtonClick() {
        if (model.getGameManager().isPlayerReady()) {
            model.getGameManager().setHasStarted(true);
            view.getStart().setVisible(false);
            view.getOpponentGridPane().setVisible(true);
            view.getWaitingForOtherPlayer().setVisible(false);

            // Start the bot attack timeline when the game starts
            startBotAttack();
        } else {
            view.getWaitingForOtherPlayer().setVisible(true);
            SceneUtil.showAlert("Players not ready", "Both players must place their ships before starting.");
        }
    }



    private void handleAttack(MouseEvent mouseEvent) {
        int size = model.getGameManager().getPlayer1().getBoard().getSizeBoard();
        double cellWidth = view.getGridPane().getWidth() / size;
        double cellHeight = view.getGridPane().getHeight() / size;
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();
        int x = (int) (mouseX / cellWidth);
        int y = (int) (mouseY / cellHeight);

        boolean isPlayingAgainstBot = model.getGameManager().isPlayingAgainstBot();

        if (isPlayingAgainstBot) {
            handleAttackAgainstBot(x, y);
        } else {
            handleAttackAgainstPlayer(x, y);
        }
    }

    private void handleAttackAgainstBot(int x, int y) {
        if (model.getGameManager().getTurn().getCurrentPlayer() == getCurrentPlayer()) {
            if (!getCurrentPlayer().hasWon(getBot())) {
                boolean successfulAttack = getCurrentPlayer().attack(getBot(), x, y);
                if (!getBot().hasWon(getCurrentPlayer())) {
                    if (successfulAttack) {
                        Platform.runLater(this::startBotAttack);
                    } else {
                        Platform.runLater(() -> SceneUtil.showAlert("Invalid Attack", "You cannot attack this cell."));
                    }
                } else {
                    Platform.runLater(() -> {
                        SceneUtil.showAlert("Game Over", "You have lost the game.");
                        if(gameManager.getPlayer2() instanceof NPC) {
                            openWinView();
                        }

                    });
                }
            } else {
                Platform.runLater(() -> {
                    SceneUtil.showAlert("Game Over", "You have already won the game.");
                    openWinView();
                });
            }
        } else {
            Platform.runLater(() -> SceneUtil.showAlert("Not your turn!", "It is the turn of " + model.getGameManager().getTurn().getCurrentPlayer().getName()));
        }
    }

    private void handleAttackAgainstPlayer(int x, int y) {
        if (model.getGameManager().getTurn().getCurrentPlayer() == getCurrentPlayer()) {
            if(!getCurrentPlayer().hasWon(gameManager.getPlayer2())) {
                getCurrentPlayer().attack(model.getGameManager().getPlayer2(), x, y);
            } else {
                Platform.runLater(() -> {
                    SceneUtil.showAlert("Game Over", getCurrentPlayer().getName()
                            +"'s has already won the game.");
                    openWinView();
                });
            }
        } else {
            SceneUtil.showAlert("Not your turn!", "It is the turn of " + model.getGameManager().getPlayer1().getName());
        }
    }

    private void startBotAttack() {
        botAttackTimer = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    Platform.runLater(() -> {
                        if (model.getGameManager().getTurn().getCurrentPlayer() == getBot()) {
                            if (!getBot().hasWon(getCurrentPlayer())) {
                                if (getBot() instanceof NPC) {
                                    boolean hit = ((NPC) getBot()).attackPlayer(getCurrentPlayer());
                                    if (!hit) {
                                        botAttackTimer.stop();
                                    }
                                }
                            }
                        } else {
                            botAttackTimer.stop();
                        }
                    });
                })
        );

        botAttackTimer.setCycleCount(Timeline.INDEFINITE);
        botAttackTimer.play();
    }

    private void openWinView() {
        WinView winView = new WinView();
        WinPresenter winPresenter = new WinPresenter(winView);
        SceneUtil.closeScene(view.getScene());

        SceneUtil.openView(winPresenter);
    }

    @Override
    public Node getView() {
        return view;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getBot() {
        return bot;
    }
}
