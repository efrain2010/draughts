import java.io.Serializable;
import java.util.ArrayList;

public class Model implements Serializable {

    final private int boardSize = 8;
    final private int numberOfPlayers = 2;
    final private String movementColor = "#add8e6";
    private int[][] boardCoords;
    private TileBtn[][] squareBtns;
    private ArrayList<Player> players = new ArrayList<Player>();
    private int playerInTurn;
    private int prevRow;
    private int newRow;
    private int prevColumn;
    private int newColumn;
    private int gameState; // 0 - waiting, 1 - start game, 2 - playing, 3 - game end

    public Model() {
        this.squareBtns = new TileBtn[this.boardSize][this.boardSize];
        this.playerInTurn = 1;
        this.gameState = 0;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public String getMovementColor() {
        return movementColor;
    }

    public int getPrevRow() {
        return prevRow;
    }

    public void setPrevRow(int prevRow) {
        this.prevRow = prevRow;
    }

    public int getNewRow() {
        return newRow;
    }

    public void setNewRow(int newRow) {
        this.newRow = newRow;
    }

    public int getPrevColumn() {
        return prevColumn;
    }

    public void setPrevColumn(int prevColumn) {
        this.prevColumn = prevColumn;
    }

    public int getNewColumn() {
        return newColumn;
    }

    public void setNewColumn(int newColumn) {
        this.newColumn = newColumn;
    }

    public int getPlayerInTurn() {
        return playerInTurn;
    }

    public void setPlayerInTurn(int playerInTurn) {
        this.playerInTurn = playerInTurn;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public TileBtn[][] getSquareBtns() {
        return squareBtns;
    }

    public void setSquareBtns(TileBtn[][] squareBtns) {
        this.squareBtns = squareBtns;
    }
    
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public void nextTurn() {
        if(this.playerInTurn + 1 > this.numberOfPlayers) {
            this.playerInTurn = 1;
        } else {
            this.playerInTurn += 1;
        }
    }

    public void addPlayer(Player newPlayer) {
        this.players.add(newPlayer);

        // for (int i = 0; i < this.boardSize; i++) {
        //     for (int j = 0; j < this.boardSize; j++) {
        //         if ((i % 2 != 0 && j % 2 != 0) || (i % 2 == 0 && j % 2 == 0)) {
        //             // black btns
        //             if(i < this.boardSize/2-1 && newPlayer.getPlayerNumber() == 1) {
        //                 Coordinates coords = new Coordinates(i, j);
        //                 newPlayer.addPiece(coords);
        //             } else if(i > this.boardSize/2 && newPlayer.getPlayerNumber() == 2) {
        //                 Piece piece = new Piece(i, j, false);
        //                 piece.setColor("white");
        //                 newPlayer.addPiece(piece);
        //             }
        //         }
        //     }
        // }
    }

}