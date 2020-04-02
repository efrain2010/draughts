import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.ArrayList;

public class Board extends JFrame implements ActionListener {

    final private int numerOfSquares = 8;
    private JButton[][] squareBtns;
    private JPanel masterPanel;
    private Player player1;
    private Player player2;

    public Board(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Draughts");
        this.setSize(520, 520);
        this.setLocationRelativeTo(null);
		this.setResizable(false);

        this.squareBtns = new JButton[this.numerOfSquares][this.numerOfSquares];

        this.masterPanel = new JPanel();
        this.masterPanel.setSize(520, 520);
        this.masterPanel.setLayout(new GridLayout(this.numerOfSquares, this.numerOfSquares, 0, 0));
        buildBoard();
        this.add(this.masterPanel);
    }

    private void buildBoard() {
        int cont = 1;
        for (int i = 0; i < this.numerOfSquares; i++) {
            for (int j = 0; j < this.numerOfSquares; j++) {
                TileBtn btn = new TileBtn(i, j);
                btn.setPreferredSize(new Dimension(100, 100));
                // JButton btn = new JButton();
                // btn.setSize(50, 50);
                // btn.setOpaque(true);
                // btn.setBorderPainted(false);
                // btn.addActionListener(this);

                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) {
                    // white btns blocked
                    btn.setEnabled(false);
                    btn.setBackground(Color.WHITE);
                } else {
                    // black btns
                    btn.setBackground(Color.BLACK);
                    System.out.println(cont++);
                }
                btn.setVisible(true);
                this.squareBtns[i][j] = btn;
                this.masterPanel.add(btn);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
		// if(e.getSource() == this.boardViewObject.getBetView().getSetDealBtn()) {
        //     checkBet();
        // }
    }

}