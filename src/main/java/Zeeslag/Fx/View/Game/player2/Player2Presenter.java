    package Zeeslag.Fx.View.Game.player2;

    import Zeeslag.Core.Player.Player;
    import Zeeslag.Fx.Manager.Presenter;
    import Zeeslag.Fx.Manager.SceneUtil;
    import Zeeslag.Fx.Model.Player1Model;
    import Zeeslag.Fx.Model.Player2Model;
    import Zeeslag.Fx.View.Game.player2.Player2View;
    import javafx.scene.Node;
    import javafx.scene.Scene;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Button;
    import javafx.scene.input.MouseButton;
    import javafx.scene.input.MouseEvent;
    import javafx.scene.layout.GridPane;
    import org.controlsfx.control.spreadsheet.Grid;

    public class Player2Presenter implements Presenter {
        private final Player2Model model;
        private final Player2View view;
        private  Player currentPlayer;

        public Player2Presenter(Player2Model model, Player2View view) {
            this.model = model;
            this.view = view;
            initializeEventHandlers();
            currentPlayer = model.gameManager.getPlayer2();
            this.updateView();
            view.getOpponentGridPane().setVisible(false);
            view.getWaitingForOtherPlayer().setVisible(false);


        }

        private void initializeEventHandlers() {
            initializeGridPaneClickHandler();
            initializeStartButtonClickHandler();
        }


        private void updateView() {
            String playerName = getCurrentPlayer().getName();
            view.getPlayerName().setText(playerName+"'s board.");
        }

        private void initializeGridPaneClickHandler() {
            GridPane gridPane = view.getGridPane();
            gridPane.setOnMouseClicked(mouseEvent -> {
                handleGridPaneClick(mouseEvent);
            });

            GridPane opponentBoard = view.getOpponentGridPane();
            opponentBoard.setOnMouseClicked(mouseEvent -> {
                if(model.gameManager.bothPlayersReady()) {
                    handleAttack(mouseEvent);
                    model.gameManager.getTurn().switchTurn();
                }
            });
        }

        private void handleGridPaneClick(MouseEvent mouseEvent) {
            double cellWidth = view.getGridPane().getWidth() / 10;
            double cellHeight = view.getGridPane().getHeight() / 10;
            MouseButton button = mouseEvent.getButton();
            double mouseX = mouseEvent.getX();
            double mouseY = mouseEvent.getY();
            int x = (int) (mouseX / cellWidth);
            int y = (int) (mouseY / cellHeight);
            System.out.println("Mouse coordinates: (" + mouseX + ", " + mouseY + ")");
            System.out.println("Clicked cell coordinates: (" + x + ", " + y + ")");

            if (button == MouseButton.PRIMARY && !model.gameManager.hasStarted()) {
                handlePrimaryClick(x, y);
            } else if (button == MouseButton.SECONDARY && !model.gameManager.hasStarted()) {
                handleSecondaryClick(x, y);
            }
        }

        private void handlePrimaryClick(int x, int y) {
            boolean successfulPlaced = model.placeHorizontalShip(x, y);
            if (getCurrentPlayer().getBoard().isAllShipsPlaced()) {
                SceneUtil.showAlert("Ships placed", "All ships are placed");
            } else if (!successfulPlaced) {
                SceneUtil.showAlert("Ship misplaced", "You cannot add a ship there!");
            }
        }

        private void handleSecondaryClick(int x, int y) {
            boolean successfulPlaced = model.placeVerticalShip(x, y);
            if (model.gameManager.getPlayer2().getBoard().isAllShipsPlaced()) {
                SceneUtil.showAlert("Ships placed", "All ships are placed");
            } else if (!successfulPlaced) {
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
            if (model.gameManager.bothPlayersReady()) {
                model.gameManager.setHasStarted(true);
                view.getStart().setVisible(false);
                view.getOpponentGridPane().setVisible(true);
                view.getWaitingForOtherPlayer().setVisible(false);
            } else {
                view.getWaitingForOtherPlayer().setVisible(true);
                SceneUtil.showAlert("Players not ready", "Both players must place their ships before starting.");
            }
        }

        private void handleAttack(MouseEvent mouseEvent) {
            double cellWidth = view.getOpponentGridPane().getWidth() / 10;
            double cellHeight = view.getOpponentGridPane().getHeight() / 10;
            MouseButton button = mouseEvent.getButton();
            double mouseX = mouseEvent.getX();
            double mouseY = mouseEvent.getY();
            int x = (int) (mouseX / cellWidth);
            int y = (int) (mouseY / cellHeight);
            System.out.println("Mouse coordinates: (" + mouseX + ", " + mouseY + ")");
            System.out.println("Clicked cell coordinates: (" + x + ", " + y + ")");

            if (button == MouseButton.PRIMARY && model.gameManager.hasStarted()) {
                if (model.gameManager.getTurn().getCurrentPlayer() == getCurrentPlayer()) {
                    getCurrentPlayer().attack(model.gameManager.getPlayer1(),x, y);
                    model.gameManager.getTurn().switchTurn();
                } else {
                    SceneUtil.showAlert("Niet jou beurt!", "Het is de beurt van"
                            + getCurrentPlayer().getName());
                }
            } else if (button == MouseButton.SECONDARY && model.gameManager.hasStarted()) {
                if (model.gameManager.getTurn().getCurrentPlayer() == getCurrentPlayer()) {
                    getCurrentPlayer().attack(model.gameManager.getPlayer1(),x, y);

                    model.gameManager.getTurn().switchTurn();
                } else {
                    SceneUtil.showAlert("Niet jou beurt!", "Het is de beurt van"
                            + getCurrentPlayer().getName());
                }
            } else {
                SceneUtil.showAlert("Spel start niet!", "Het spel is nog niet begonnen."
                        +getCurrentPlayer().getName());
            }
        }

        public Player getCurrentPlayer() {
            return currentPlayer;
        }

        @Override
        public Node getView() {
            return view;
        }
    }
