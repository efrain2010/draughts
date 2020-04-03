import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

    private String name;
    private int playerNumber;
    private ArrayList<Piece> pieces;
    
    public Player(String name) {
        this.name = name;
        this.pieces = new ArrayList<Piece>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    public void setStakes(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public ArrayList<Piece> getPieces() {
        return this.pieces;
    }

    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }
    
    public void removeStake(Piece piece) {
        this.pieces.remove(piece);
    }

    public String toString() {
        return "I am " + this.name;
    }

}