import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TileBtn extends JButton {

    private int row;
    private int column;
    private Piece boardPiece;

    public TileBtn(int row, int column) {
        this.row = row;
        this.column = column;

        this.setSize(50, 50);
        this.setOpaque(true);
        this.setBorderPainted(false);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Piece getBoardPiece() {
        return boardPiece;
    }

    public void setBoardPiece(Piece boardPiece) {
        this.boardPiece = boardPiece;
    }
    
    public void removeBoardPiece() {
        this.setIcon(null);
        this.boardPiece = null;
    }

    public void crownPiece() {
        this.boardPiece.setIsKing(true);
        this.setIcon(new ImageIcon(this.boardPiece.getImage()));
    }

}