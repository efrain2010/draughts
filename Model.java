import java.io.Serializable;

public class Model implements Serializable {

    final private int boardSize = 8;
    final private int numberOfPlayers = 2;
    final private String movementColor = "#add8e6";
    final private int[][] boardCoords;
    private int prevRow;
    private int newRow;
    private int prevColumn;
    private int newColumn;
    

    public Model() {
        this.boardCoords = new int[this.boardSize][this.boardSize];
        createServerBoard();
    }

    private void createServerBoard() {
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) {
                    // white btns
                    this.boardCoords[i][j] = 0;
                } else {
                    // black btns
                    this.boardCoords[i][j] = 1;   
                }
            }
        }
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

}