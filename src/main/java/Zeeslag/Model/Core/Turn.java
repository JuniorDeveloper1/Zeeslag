package Zeeslag.Model.Core;

public class Turn {
    private Player currentPlayer;
    private boolean hasHitTarget;

    public Turn(Player startingPlayer) {
        this.currentPlayer = startingPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setPlayerTurn(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean hasHitTarget() {
        return hasHitTarget;
    }

    public void setHasHitTarget(boolean hasHitTarget) {
        this.hasHitTarget = hasHitTarget;
    }
}
