
public class Game {

    // private Player[] board = new Player[9];
    private TileBtn[][] squareBtns = new TileBtn[8][8];
    private Player currentPlayer;
    private int[] prevMove;
    private int[] newMove;

    public Game() {
        
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public TileBtn[][] getSquareBtns() {
        return squareBtns;
    }

    public void setSquareBtns(TileBtn[][] squareBtns) {
        this.squareBtns = squareBtns;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int[] getPrevMove() {
        return prevMove;
    }

    public void setPrevMove(int[] prevMove) {
        this.prevMove = prevMove;
    }

    public int[] getNewMove() {
        return newMove;
    }

    public void setNewMove(int[] newMove) {
        this.newMove = newMove;
    }

}