    package Zeeslag;

    import com.almasb.fxgl.entity.GameWorld;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.stage.Stage;

    import java.io.IOException;

    public class GameManager {
        private GameManager(){}

        private Game game;

        private Player player1;

        private Player player2;

        /**
         * We kunnen hier Highscoore, Aantal clicks, ... adden
         */

        private static GameManager gameManager;

        public static GameManager getInstance(){
            if(GameManager.gameManager == null){
                System.out.println("NEW MANAGER CREATED");
                GameManager.gameManager = new GameManager();
            }

            return GameManager.gameManager;
        }

        public void startGame(){
            gameManager.setGame(new Game());
            gameManager.setPlayer1(new Player(null, null));
            gameManager.setPlayer2(new Player(null, null));
        }

        public Player getPlayer1() {
            return player1;
        }

        public void setPlayer1(Player player1) {
            this.player1 = player1;
        }

        public Player getPlayer2() {
            return player2;
        }

        public void setPlayer2(Player player2) {
            this.player2 = player2;
        }

        public Game getGame() {
            return game;
        }

        private void setGame(Game game) {
            this.game = game;
        }

        /**
         * Further game logic
         */

        public void openScene(String fxmlFile, String title) throws IOException {
            Parent root = FXMLLoader.load(GameManager.class.getClassLoader().getResource(fxmlFile));
            Stage stage = new Stage();
            if(title == null) {title = "Default";}
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        }

        public void closeScene(Button button){
            Stage currentStage = (Stage) button.getScene().getWindow();
            currentStage.close();
        }
    }
