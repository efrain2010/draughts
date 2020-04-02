import java.awt.Dimension;

import javax.swing.JButton;

public class TileBtn extends JButton {

    private int x;
    private int y;
    private Piece boardPiece;

    public TileBtn(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Piece getBoardPiece() {
        return boardPiece;
    }

    public void setBoardPiece(Piece boardPiece) {
        this.boardPiece = boardPiece;
    }

}