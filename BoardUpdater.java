import java.io.Serializable;

public class BoardUpdater implements Serializable {

    private int prevRow;
    private int newRow;
    private int prevColumn;
    private int newColumn;
    private int numOfPlayer;
    private boolean sendingCoords;

    public BoardUpdater(int prevRow, int newRow, int prevColumn, int newColumn) {
        this.prevRow = prevRow;
        this.newRow = newRow;
        this.prevColumn = prevColumn;
        this.newColumn = newColumn;
        this.sendingCoords = true;
    }
    
    public BoardUpdater(int numOfPlayer) {
        this.numOfPlayer = numOfPlayer;
        this.sendingCoords = false;
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
        return numOfPlayer;
    }

    public void setNumOfPlayer(int numOfPlayer) {
        this.numOfPlayer = numOfPlayer;
    }

    public boolean getSendingCoords() {
        return sendingCoords;
    }

    public void setSendingCoords(boolean isSendingCoords) {
        this.sendingCoords = isSendingCoords;
    }

    public String toString() {
        return "I'm player " + this.getNumOfPlayer();
    }

}