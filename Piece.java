import java.awt.Cursor;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Piece extends JButton implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String color;
    private int row;
    private int column;
    private boolean isKing;
    private Player player;

    public Piece(int row, int column, boolean isKing) {
        this.row = row;
        this.column = column;
        this.isKing = isKing;
        
        this.setSize(50, 50);
        this.setOpaque(false);
        this.setBorderPainted(false);
        // this.setImage();
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean getIsKing() {
        return this.isKing;
    }

    public void setIsKing(boolean isKing) {
        this.isKing = isKing;
    }

    public String getImage() {
        String image = this.color + ".png";
        if(this.isKing == true) {
            image = "king-" + image;
        }
        return image;
    }

    public String toString() {
        return color + " piece, coords: " + row + " - " + column;
    }

    public boolean isPlayerOne() {
        return this.color == "white";
    }
    
    public boolean isSamePlayer(Piece piece) {
        return this.player == piece.getPlayer();
    }

}