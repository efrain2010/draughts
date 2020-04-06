import java.io.Serializable;

public class ModelUpdater implements Serializable {

    private int prevRow;
    private int newRow;
    private int prevColumn;
    private int newColumn;
    private int playerNumber;
    private boolean sendingCoords;
    private String playerName;
    private int gameState;
    private Player player;
    private int boardSize;

    public ModelUpdater(int prevRow, int newRow, int prevColumn, int newColumn) {
        this.prevRow = prevRow;
        this.newRow = newRow;
        this.prevColumn = prevColumn;
        this.newColumn = newColumn;
        this.sendingCoords = true;
        this.gameState = 1;
    }
    
    public ModelUpdater(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    
    public ModelUpdater(String playerName) {
        this.playerName = playerName;
        this.gameState = 0;
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

    public int getNumOfPlayer() {
        return playerNumber;
    }

    public void setNumOfPlayer(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public boolean getSendingCoords() {
        return sendingCoords;
    }

    public void setSendingCoords(boolean isSendingCoords) {
        this.sendingCoords = isSendingCoords;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public String toString() {
        return "I'm player " + this.player.getPlayerNumber();
    }

}