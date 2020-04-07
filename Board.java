import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.HashMap;

public class Board extends JFrame {

    private Controller controllerObject;

    private TileBtn[][] squareBtns;
    private JPanel masterPanel, boardPanel, initPanel, controlsPanel;
    private Piece pieceInMove;
    private JButton newGameBtn, endGameBtn, playerOneBtn, playerTwoBtn;
    private JLabel winnerLabel, statusLabel;
    private ArrayList<TileBtn> possibleMoves, clickableBtns;
    private HashMap<TileBtn, TileBtn> eatMoves;
    private String movementColor = "#add8e6";
    private int boardSize = 8;
    
    public Board(Controller controllerObject) {
        this.controllerObject = controllerObject;
        this.squareBtns = new TileBtn[this.boardSize][this.boardSize];

        this.controllerObject.setPlayer(new Player(JOptionPane.showInputDialog(new JFrame(), "New Player name")));

        setdata();
        createScreen();
    }
    
    public void startGame(int boardSize) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initPanel.setVisible(false);
                boardPanel.setVisible(true);
                masterPanel.remove(initPanel);
                masterPanel.add(boardPanel);
                // masterPanel.add(controlsPanel, BorderLayout.SOUTH);
                // masterPanel.add(boardPanel, BorderLayout.CENTER);
                createBoard();
                setPlayers();
            }
        });
    }

    private void setdata() {
        this.possibleMoves = new ArrayList<TileBtn>();
        this.clickableBtns = new ArrayList<TileBtn>();
        this.eatMoves = new HashMap<TileBtn, TileBtn>();
    }

    public Piece getBoardPiece(int row, int column) {
        return this.squareBtns[row][column].getBoardPiece();
    }

    public void setPlayer(int num, Player player) {
        this.newGameBtn.setEnabled(true);
    }

    private void createScreen() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Draughts");
        this.setSize(520, 520);
        this.setLocationRelativeTo(null);
        
        this.masterPanel = new JPanel();
        this.masterPanel.setSize(520, 520);
        // this.masterPanel.setLayout(new BorderLayout());
        this.masterPanel.setLayout(new BoxLayout(this.masterPanel, BoxLayout.Y_AXIS));
        
        this.initPanel = new JPanel();
        this.initPanel.setLayout(new BorderLayout());
        this.initPanel.setSize(520,520);
        this.statusLabel = new JLabel("<html><body style='text-align:center;'>Hello " + this.controllerObject.getPlayer().getName() + "<br>we need a second player...</body></html>");
        this.initPanel.add(this.statusLabel, BorderLayout.CENTER);
        
        this.newGameBtn = new JButton("Start Again");
        this.newGameBtn.addActionListener(this.controllerObject);
        this.newGameBtn.setVisible(false);        

        this.boardPanel = new JPanel();
        this.boardPanel.setVisible(false);
        this.boardPanel.setSize(520, 520);
        this.boardPanel.setLayout(new GridLayout(this.boardSize, this.boardSize, 0, 0));
        
        this.masterPanel.add(this.initPanel);
        this.masterPanel.add(this.boardPanel);
        this.add(this.masterPanel);

        this.setVisible(true);
    }

    private void createBoard() {
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                TileBtn btn = new TileBtn(i, j);

                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) {
                    // white btns blocked
                    btn.setEnabled(false);
                    btn.setBackground(Color.WHITE);
                } else {
                    // black btns
                    btn.setBackground(Color.BLACK);
                    btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    this.clickableBtns.add(btn);
                }
                this.squareBtns[i][j] = btn;
                this.boardPanel.add(btn);
                this.squareBtns[i][j].addActionListener(this.controllerObject);
            }
        }
    }

    public void setPlayers() {
        for (int i = 0; i < this.squareBtns.length; i++) {
            for (int j = 0; j < this.squareBtns[i].length; j++) {
                if ((i % 2 == 0 && j % 2 != 0) || (i % 2 != 0 && j % 2 == 0)) {
                    Piece piece = new Piece(i, j, false);
                    if(i < this.boardSize/2-1) {
                        piece.setColor("red");
                        if(this.controllerObject.getPlayer().getPlayerNumber() == 2) {
                            piece.setPlayer(this.controllerObject.getPlayer());
                            this.controllerObject.getPlayer().addPiece(piece);
                        }
                        this.squareBtns[i][j].setBoardPiece(piece);
                        this.squareBtns[i][j].setIcon(new ImageIcon(piece.getImage()));
                    } else if(i > this.boardSize/2) {
                        piece.setColor("white");
                        if(this.controllerObject.getPlayer().getPlayerNumber() == 1) {
                            piece.setPlayer(this.controllerObject.getPlayer());
                            this.controllerObject.getPlayer().addPiece(piece);
                        }
                        this.squareBtns[i][j].setBoardPiece(piece);
                        this.squareBtns[i][j].setIcon(new ImageIcon(piece.getImage()));
                    }
                }
            }
        }
    }

    public void setNewGame() {
        this.newGameBtn.setVisible(false);
        this.endGameBtn.setVisible(true);
        this.playerOneBtn.setVisible(false);
        this.playerTwoBtn.setVisible(false);
        setdata();
        setPlayers();
        // this.winnerLabel.setText(this.playerInTurn.getName()+"'s turn");
        // this.winnerLabel.setVisible(true);
    }
    
    public void endGame() {
        hidePossibleMoves();
        setdata();
        this.winnerLabel.setText("");
        this.winnerLabel.setVisible(false);
        for(TileBtn btn : clickableBtns) {
            btn.setBackground(Color.BLACK);
            btn.removeBoardPiece();
        }
        for (int i = 0; i < this.squareBtns.length; i++) {
            for (int j = 0; j < this.squareBtns[i].length; j++) {
                this.squareBtns[i][j].removeBoardPiece();
            }
        }
        this.newGameBtn.setVisible(true);
        this.endGameBtn.setVisible(false);
    }

    public JButton getNewGameBtn() {
        return this.newGameBtn;
    }

    public JButton getEndGameBtn() {
        return this.endGameBtn;
    }

    public JButton getPlayerOneBtn() {
        return this.playerOneBtn;
    }

    public JButton getPlayerTwoBtn() {
        return this.playerTwoBtn;
    }

    public JButton[][] getSquareBtns() {
        return this.squareBtns;
    }

    public Piece getPieceInMove() {
        return this.pieceInMove;
    }
    
    public ArrayList<TileBtn> getPossibleMoves() {
        return possibleMoves;
    }
    
    public void setPieceInMove(Piece piece) {
        this.pieceInMove = piece;
    }

    public void movePiece(int prevRow, int prevColumn, int newRow, int newColumn) {
        takePiece(prevRow, prevColumn);
        putPiece(newRow, newColumn);
    }

    public void takePiece(int row, int column) {
        this.pieceInMove = this.squareBtns[row][column].getBoardPiece();
        this.squareBtns[row][column].removeBoardPiece();
    }
    
    public void putPiece(int row, int column) {

        TileBtn tileBtn = this.squareBtns[row][column];

        if(this.controllerObject.itIsMyTurn()) {
            hidePossibleMoves();
        }

        checkIfAte(tileBtn);
        
        if(tileBtn.getBoardPiece() == null) {
            this.pieceInMove.setRow(row);
            this.pieceInMove.setColumn(column);
            tileBtn.setIcon(new ImageIcon(this.pieceInMove.getImage()));
            tileBtn.setBoardPiece(this.pieceInMove);
            checkIfCrowned(tileBtn);
            this.pieceInMove = null;
            this.possibleMoves.clear();
            this.controllerObject.checkWinner();
            this.controllerObject.changeTurn();
        }
    }

    public void showWinner(Player player) {
        // this.winnerLabel.setText("PLAYER " + player.getName() + " WON!!!");
        // for(TileBtn btn : this.clickableBtns) {
        //     btn.setEnabled(false);
        // }
    }
    
    public void checkIfLose(int piecesLeft) {
        if(piecesLeft == 11) {
            if(this.controllerObject.itIsMyTurn()) {
                System.out.println("I Lose");
            } else {
                System.out.println("I Won");
            }
        }
        // this.winnerLabel.setText("PLAYER " + player.getName() + " WON!!!");
        // for(TileBtn btn : this.clickableBtns) {
        //     btn.setEnabled(false);
        // }
    }

    private void checkIfCrowned(TileBtn btn) {
        if(btn.getBoardPiece().isPlayerOne()) {
            if(btn.getBoardPiece().getRow() == 0) {
                btn.crownPiece();
            }
        } else {
            if(btn.getBoardPiece().getRow() == this.boardSize-1) {
                btn.crownPiece();
            }
        }
    }

    public void checkPossibleMoves() {
        this.squareBtns[this.pieceInMove.getRow()][this.pieceInMove.getColumn()].setBackground(Color.decode("#add8e6"));
        this.possibleMoves.add(this.squareBtns[this.pieceInMove.getRow()][this.pieceInMove.getColumn()]);
        if(this.pieceInMove.isPlayerOne()) {
            checkMoveQuadrant1();
            checkMoveQuadrant2();
            if(this.pieceInMove.getIsKing()) {
                checkMoveQuadrant3();
                checkMoveQuadrant4();
            }
        } else {
            checkMoveQuadrant3();
            checkMoveQuadrant4();
            if(this.pieceInMove.getIsKing()) {
                checkMoveQuadrant1();
                checkMoveQuadrant2();
            }
        }
    }

    private void hidePossibleMoves() {
        for(TileBtn btn : this.possibleMoves) {
            btn.setBackground(Color.BLACK);
        }
    }

    private void checkMoveQuadrant1() {
        if(this.pieceInMove.getRow()-1 >= 0  && this.pieceInMove.getColumn()+1 < this.boardSize) {
            if(this.squareBtns[this.pieceInMove.getRow()-1][this.pieceInMove.getColumn()+1].getBoardPiece() == null) {
                this.squareBtns[this.pieceInMove.getRow()-1][this.pieceInMove.getColumn()+1].setBackground(Color.decode(movementColor));
                this.possibleMoves.add(this.squareBtns[this.pieceInMove.getRow()-1][this.pieceInMove.getColumn()+1]);
            } else {
                if(this.pieceInMove.getRow()-2 >= 0 && this.pieceInMove.getColumn()+2 < this.boardSize) {
                    if(!this.squareBtns[this.pieceInMove.getRow()-1][this.pieceInMove.getColumn()+1].getBoardPiece().isSamePlayer(this.pieceInMove)) {
                        if(this.squareBtns[this.pieceInMove.getRow()-2][this.pieceInMove.getColumn()+2].getBoardPiece() == null) {
                            this.squareBtns[this.pieceInMove.getRow()-2][this.pieceInMove.getColumn()+2].setBackground(Color.decode(movementColor));
                            this.possibleMoves.add(this.squareBtns[this.pieceInMove.getRow()-2][this.pieceInMove.getColumn()+2]);
                            this.eatMoves.put(this.squareBtns[this.pieceInMove.getRow()-2][this.pieceInMove.getColumn()+2], this.squareBtns[this.pieceInMove.getRow()-1][this.pieceInMove.getColumn()+1]);
                        }
                    }
                }
            }
        }
    }

    private void checkMoveQuadrant2() {
        if(this.pieceInMove.getRow()-1 >= 0 && this.pieceInMove.getColumn()-1 >= 0) {
            if(this.squareBtns[this.pieceInMove.getRow()-1][this.pieceInMove.getColumn()-1].getBoardPiece() == null) {
                this.squareBtns[this.pieceInMove.getRow()-1][this.pieceInMove.getColumn()-1].setBackground(Color.decode(movementColor));
                this.possibleMoves.add(this.squareBtns[this.pieceInMove.getRow()-1][this.pieceInMove.getColumn()-1]);
            } else {
                if(this.pieceInMove.getRow()-2 >= 0 && this.pieceInMove.getColumn()-2 >= 0) {
                    if(!this.squareBtns[this.pieceInMove.getRow()-1][this.pieceInMove.getColumn()-1].getBoardPiece().isSamePlayer(this.pieceInMove)) {
                        if(this.squareBtns[this.pieceInMove.getRow()-2][this.pieceInMove.getColumn()-2].getBoardPiece() == null) {
                            this.squareBtns[this.pieceInMove.getRow()-2][this.pieceInMove.getColumn()-2].setBackground(Color.decode(movementColor));
                            this.possibleMoves.add(this.squareBtns[this.pieceInMove.getRow()-2][this.pieceInMove.getColumn()-2]);
                            this.eatMoves.put(this.squareBtns[this.pieceInMove.getRow()-2][this.pieceInMove.getColumn()-2], this.squareBtns[this.pieceInMove.getRow()-1][this.pieceInMove.getColumn()-1]);
                        }
                    }
                }
            }
        }
    }
    
    private void checkMoveQuadrant3() {
        if(this.pieceInMove.getRow()+1 < this.boardSize && this.pieceInMove.getColumn()-1 >= 0) {
            if(this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()-1].getBoardPiece() == null) {
                this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()-1].setBackground(Color.decode(movementColor));
                this.possibleMoves.add(this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()-1]);
            } else {
                if(this.pieceInMove.getRow()+2 < this.boardSize && this.pieceInMove.getColumn()-2 >= 0) {
                    if(!this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()-1].getBoardPiece().isSamePlayer(this.pieceInMove)) {
                        if(this.squareBtns[this.pieceInMove.getRow()+2][this.pieceInMove.getColumn()-2].getBoardPiece() == null) {
                            this.squareBtns[this.pieceInMove.getRow()+2][this.pieceInMove.getColumn()-2].setBackground(Color.decode(movementColor));
                            this.possibleMoves.add(this.squareBtns[this.pieceInMove.getRow()+2][this.pieceInMove.getColumn()-2]);
                            this.eatMoves.put(this.squareBtns[this.pieceInMove.getRow()+2][this.pieceInMove.getColumn()-2], this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()-1]);
                        }
                    }
                }
            }
        }
    }
    
    private void checkMoveQuadrant4() {
        if(this.pieceInMove.getRow()+1 < this.boardSize && this.pieceInMove.getColumn()+1 < this.boardSize) {
            if(this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()+1].getBoardPiece() == null) {
                this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()+1].setBackground(Color.decode("#add8e6"));
                this.possibleMoves.add(this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()+1]);
            } else {
                if(this.pieceInMove.getRow()+2 < this.boardSize && this.pieceInMove.getColumn()+2 < this.boardSize) {
                    if(!this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()+1].getBoardPiece().isSamePlayer(this.pieceInMove)) {
                        if(this.squareBtns[this.pieceInMove.getRow()+2][this.pieceInMove.getColumn()+2].getBoardPiece() == null) {
                            this.squareBtns[this.pieceInMove.getRow()+2][this.pieceInMove.getColumn()+2].setBackground(Color.decode("#add8e6"));
                            this.possibleMoves.add(this.squareBtns[this.pieceInMove.getRow()+2][this.pieceInMove.getColumn()+2]);
                            this.eatMoves.put(this.squareBtns[this.pieceInMove.getRow()+2][this.pieceInMove.getColumn()+2], this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()+1]);
                        }
                    }
                }
            }
        }
    }

    private void checkIfAte(TileBtn tileBtn) {

        int row = -1, column = -1;
        if(this.pieceInMove.getRow() < tileBtn.getRow()) {
            if(tileBtn.getRow() - this.pieceInMove.getRow() == 2) {
                row = (tileBtn.getRow() + this.pieceInMove.getRow())/2;
            }
        } else {
            if(this.pieceInMove.getRow() - tileBtn.getRow() == 2) {
                row = (tileBtn.getRow() + this.pieceInMove.getRow())/2;
            }
        }
        
        if(this.pieceInMove.getColumn() < tileBtn.getColumn()) {
            if(tileBtn.getColumn() - this.pieceInMove.getColumn() == 2) {
                column = (tileBtn.getColumn() + this.pieceInMove.getColumn())/2;
            }
        } else {
            if(this.pieceInMove.getColumn() - tileBtn.getColumn() == 2) {
                column = (tileBtn.getColumn() + this.pieceInMove.getColumn())/2;
            }
        }
        
        if(row != -1 && column != -1) {
            Piece piece = this.squareBtns[row][column].getBoardPiece();
            if(!this.controllerObject.itIsMyTurn()) {
                this.controllerObject.getPlayer().removeStake(piece);
                checkIfLose(this.controllerObject.getPlayer().getPieces().size());
            }
            this.squareBtns[row][column].setIcon(new ImageIcon(""));
            this.squareBtns[row][column].setBoardPiece(null);
        }

    }

    public void checkIfPlayerCanEat() {
        for(Piece piece : this.controllerObject.getPlayer().getPieces()) {
            if(this.controllerObject.getPlayer().getPlayerNumber() == 1) {
                checkEatQuadrant1(piece.getRow(), piece.getColumn());
                checkEatQuadrant2(piece.getRow(), piece.getColumn());
                if(piece.getIsKing()) {
                    checkEatQuadrant3(piece.getRow(), piece.getColumn());
                    checkEatQuadrant4(piece.getRow(), piece.getColumn());
                }
            } else {
                checkEatQuadrant3(piece.getRow(), piece.getColumn());
                checkEatQuadrant4(piece.getRow(), piece.getColumn());
                if(piece.getIsKing()) {
                    checkEatQuadrant1(piece.getRow(), piece.getColumn());
                    checkEatQuadrant2(piece.getRow(), piece.getColumn());
                }
            }
        }
    }

    private void checkEatQuadrant1(int row, int column) {
        if(row-1 >= 0  && column+1 < this.boardSize) {
            if(this.squareBtns[row-1][column+1].getBoardPiece() != null) {
                if(row-2 >= 0 && column+2 < this.boardSize) {
                    if(this.squareBtns[row-1][column+1].getBoardPiece().getPlayer() == null) {
                        if(this.squareBtns[row-2][column+2].getBoardPiece() == null) {
                            this.squareBtns[row-2][column+2].setBackground(Color.RED);
                            this.possibleMoves.add(this.squareBtns[row-2][column+2]);
                        }
                    }
                }
            }
        }
    }

    private void checkEatQuadrant2(int row, int column) {
        if(row-1 >= 0 && column-1 >= 0) {
            if(this.squareBtns[row-1][column-1].getBoardPiece() != null) {
                if(row-2 >= 0 && column-2 >= 0) {
                    if(this.squareBtns[row-1][column-1].getBoardPiece().getPlayer() == null) {
                        if(this.squareBtns[row-2][column-2].getBoardPiece() == null) {
                            this.squareBtns[row-2][column-2].setBackground(Color.RED);
                            this.possibleMoves.add(this.squareBtns[row-2][column-2]);
                        }
                    }
                }
            }
        }
    }

    private void checkEatQuadrant3(int row, int column) {
        if(row+1 < this.boardSize && column-1 >= 0) {
            if(this.squareBtns[row+1][column-1].getBoardPiece() != null) {
                if(row+2 < this.boardSize && column-2 >= 0) {
                    if(this.squareBtns[row+1][column-1].getBoardPiece().getPlayer() == null) {
                        if(this.squareBtns[row+2][column-2].getBoardPiece() == null) {
                            this.squareBtns[row+2][column-2].setBackground(Color.RED);
                            this.possibleMoves.add(this.squareBtns[row+2][column-2]);
                        }
                    }
                }
            }
        }
    }
    
    private void checkEatQuadrant4(int row, int column) {
        if(row+1 < this.boardSize && column+1 < this.boardSize) {
            if(this.squareBtns[row+1][column+1].getBoardPiece() != null) {
                if(row+2 < this.boardSize && column+2 < this.boardSize) {
                    if(this.squareBtns[row+1][column+1].getBoardPiece().getPlayer() == null) {
                        if(this.squareBtns[row+2][column+2].getBoardPiece() == null) {
                            this.squareBtns[row+2][column+2].setBackground(Color.RED);
                            this.possibleMoves.add(this.squareBtns[row+2][column+2]);
                        }
                    }
                }
            }
        }
    }

}