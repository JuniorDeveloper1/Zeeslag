    package Zeeslag.Core.Board;

    import Zeeslag.Core.Bomb.Bomb;
    import Zeeslag.Core.Dimension.Dimension;
    import Zeeslag.Core.Piece.Piece;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Random;

    public class Board extends Dimension {
        private List<Piece> pieces;
        private List<Bomb> bombs;


        public Board() {
            super(10, 10);
            this.pieces = new ArrayList<>();
            this.bombs = new ArrayList<>();
            this.generateDefaultPieces();
        }

        public Board(int width, int height) {
            super(width, height);
            this.pieces = new ArrayList<>();
            this.bombs = new ArrayList<>();
            this.generateDefaultPieces();
        }

        public Board(int width, int height, List<Piece> pieces) {
            super(width, height);
            this.pieces = pieces;
        }


        public void generateDefaultPieces() {
            this.getPieces().add(new Piece(4));
            this.getPieces().add(new Piece(3));
            this.getPieces().add(new Piece(3));
            this.getPieces().add(new Piece(2));
            this.getPieces().add(new Piece(2));
            this.getPieces().add(new Piece(2));
        }

        private boolean isCoordOnBoard(int coordX, int coordY) {
            return coordX >= 1 && coordX <= this.getHeight() && coordY >= 1 && coordY <= this.getWidth();
        }

        private boolean isCoordOnPiece(int coordX, int coordY) {
            for (int i = 0; i < this.getPieces().size(); i++) {
                if (this.getPieces().get(i).isPlaced()) {
                    for (int y = 0; y < this.getPieces().get(i).getWidth(); y++) {
                        for (int x = 0; x < this.getPieces().get(i).getHeight(); x++) {
                            int pieceY = this.getPieces().get(i).getCoord().getY() + y;
                            int pieceX = this.getPieces().get(i).getCoord().getX() + x;
                            if (coordX == pieceX && coordY == pieceY) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

        public boolean canPieceBePlaced(Piece piece) {
            for (int i = 0; i < piece.getWidth(); i++) {
                for (int j = 0; i < piece.getHeight(); j++) {
                    int coordY = piece.getCoord().getY() + i;
                    int coordX = piece.getCoord().getX() + j;
                    if (!this.isCoordOnPiece(coordX, coordY) || !this.isCoordOnBoard(coordX, coordY)) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void placePieceRandomly(Piece piece) {
            Random random = new Random();

            int randomX = random.nextInt(this.getHeight()) + 1;
            int randomY = random.nextInt(this.getWidth()) + 1;
            piece.getCoord().setCoords(randomX, randomY);

            boolean rotate = random.nextBoolean();
            if (rotate) {
                piece.rotate();
            }

            while (!this.canPieceBePlaced(piece)) {
                randomX = random.nextInt(this.getHeight()) + 1;
                randomY = random.nextInt(this.getWidth()) + 1;

                piece.getCoord().setCoords(randomX, randomY);

                rotate = random.nextBoolean();
                if (rotate) {
                    piece.rotate();
                }
            }

            piece.lock();
        }

        public void placeAllRandomly() {
            for (int i = 0; i < this.getPieces().size(); i++) {
                this.placePieceRandomly(this.getPieces().get(i));
            }
        }

        public boolean recieveBomb(Bomb bomb) {
            this.getBombs().add(bomb);

            if (isCoordOnPiece(bomb.getCoord().getX(), bomb.getCoord().getY())) {
                bomb.setHit(true);
                return true;
            }

            return false;
        }

        // Getters and Setters
        public List<Piece> getPieces() {
            return pieces;
        }

        public void setPieces(List<Piece> pieces) {
            this.pieces = pieces;
        }

        public List<Bomb> getBombs() {
            return bombs;
        }

        public void setBombs(List<Bomb> bombs) {
            this.bombs = bombs;
        }

    }
