import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

public class Player implements Serializable {

    private int name;
    private int playerNumber;
    private ArrayList<Piece> pieces;
    private Socket socket;
    private Player opponent;
    private Game gameObject;
    
    public Player(Game game, Socket socket, int playerNumber) {
        this.gameObject = game;
        this.socket = socket;
        this.playerNumber = playerNumber;

        System.out.println(playerNumber);
        // if(playerNumber == 1) {
        //     this.gameObject.setCurrentPlayer(this);
        // } else {
        //     this.opponent = this.gameObject.getCurrentPlayer();
        //     // this.opponent.opponent = this;
        // }
        this.pieces = new ArrayList<Piece>();
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getName() {
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