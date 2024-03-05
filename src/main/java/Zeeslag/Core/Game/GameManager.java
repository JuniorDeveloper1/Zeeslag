    package Zeeslag.Core.Game;

    import Zeeslag.Core.Board.Board;
    import Zeeslag.Core.Player.Player;

    public class GameManager {
        private GameManager(){}

       // private Game game;

        private Player player1;

        private Player player2;
        private boolean hasStarted = false;

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
                //gameManager.setGame(new Game());
                gameManager.setPlayer1(new Player("Player1", new Board(10,10)));
                gameManager.setPlayer2(new Player("Player2", new Board(10 ,10)));
                setHasStarted(true);
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

        public boolean isHasStarted() {
            return hasStarted;
        }

        public void setHasStarted(boolean hasStarted) {
            this.hasStarted = hasStarted;
        }

        //public Game getGame() {
       //     return game;
       // }

       // private void setGame(Game game) {
       //     this.game = game;
       // }





        /**
         * Further game logic
         */

    }
