package Zeeslag;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args){

        Game game = new Game();
        game.loadDefault();


        Player player1 = new Player("Nick", game.getBoard1());

        Player player2 = new Player("Noah", game.getBoard1());


        player1.attack(player2, 5 , 4);


        //THIS IS WRONG you player2 cannot attack player2
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Zeeslag");
        stage.setScene(scene);
        stage.show();
    }
}