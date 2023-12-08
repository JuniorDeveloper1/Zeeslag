package Zeeslag;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        game.loadDefault();


        Player player1 = new Player("Nick", game.getBoard1());

        Player player2 = new Player("Noah", game.getBoard1());


        player1.attack(player2, 5 , 4);


        //THIS IS WRONG you player2 cannot attack player2
    }

}