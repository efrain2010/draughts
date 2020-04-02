import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Piece extends JButton {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String color;
    private int x;
    private int y;
    private boolean isKing;

    public Piece(int x, int y, boolean isKing) {
        this.x = x;
        this.y = y;
        this.isKing = isKing;
        
        this.setSize(50, 50);
        this.setOpaque(false);
        this.setBorderPainted(false);
        this.setImage();
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public boolean getIsKing() {
        return this.isKing;
    }

    public void setIsKing(boolean isKing) {
        this.isKing = isKing;
    }

    public void setImage() {
        String image = this.color + ".png";
        if(this.isKing == true) {
            image = "king-" + image;
        }
        this.setIcon(new ImageIcon(image));
    }

    public String toString() {
        return color + " piece, coords: " + x + " - " + y;
    }

}