import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

    private String name;
    private int playerNumber;
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private Player opponent;
    
    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    
    public Player(String name) {
        this.name = name;
    }

    public Player(String name, int playerNumber) {
        this.name = name;
        this.playerNumber = playerNumber;
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
        String text = "I am " + this.name;
        if(this.playerNumber > 0) {
            text += " with number " + this.playerNumber;
        }
        return text;
    }

}