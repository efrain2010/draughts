import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.HashMap;

public class Board extends JFrame {

    private Controller controllerObject;

    private TileBtn[][] squareBtns;
    private JPanel masterPanel, boardPanel, initPanel;
    private Piece pieceInMove;
    private JButton newGameBtn, endGameBtn, playerOneBtn, playerTwoBtn;
    private JLabel winnerLabel, statusLabel;
    private ArrayList<TileBtn> possibleMoves, clickableBtns;
    private HashMap<TileBtn, TileBtn> eatMoves;
    private String movementColor = "#add8e6";
    private String playerName;
    private int boardSize = 8;
    
    public Board(Controller controllerObject) {
        this.controllerObject = controllerObject;
        // this.playerInTurn = controllerObject.getPlayers().get(0);
        // this.playerOnePieces = new ArrayList<Piece>();
        // this.playerTwoPieces = new ArrayList<Piece>();
        this.squareBtns = new TileBtn[this.boardSize][this.boardSize];

        this.controllerObject.setPlayer(new Player(JOptionPane.showInputDialog(new JFrame(), "New Player name")));
        // this.controllerObject.s
        setdata();
        createScreen();
        createBoard();
    }

    public void startGame(int boardSize) {
        this.boardSize = boardSize;
        this.initPanel.setVisible(false);
        this.masterPanel.remove(this.initPanel);
        this.masterPanel.add(this.boardPanel);
    }

    private void setdata() {
        this.possibleMoves = new ArrayList<TileBtn>();
        this.clickableBtns = new ArrayList<TileBtn>();
        this.eatMoves = new HashMap<TileBtn, TileBtn>();
    }

    public void setPlayer(int num, Player player) {
        // if(num == 1) {
        //     this.playerOne = player;
        //     this.playerOne.setStakes(this.playerOnePieces);
        // } else {
        //     this.playerTwo = player;
        //     this.playerTwo.setStakes(this.playerTwoPieces);
        // }
        this.newGameBtn.setEnabled(true);
    }

    private void createScreen() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Draughts");
        this.setSize(520, 520);
        this.setLocationRelativeTo(null);
        
        this.masterPanel = new JPanel();
        this.masterPanel.setSize(520, 520);
        this.masterPanel.setLayout(new BoxLayout(this.masterPanel, BoxLayout.Y_AXIS));

        this.initPanel = new JPanel();
        this.initPanel.setLayout(new BorderLayout());
        // JPanel controlsPanel = new JPanel();
        // // controlsPanel.setSize(520, 30);
        // controlsPanel.setPreferredSize(new Dimension(520, 30));
        // controlsPanel.setBackground(Color.decode("#75777b"));
        // controlsPanel.setLayout(new FlowLayout());

        // this.winnerLabel = new JLabel();
        // controlsPanel.add(this.winnerLabel);
        
        this.initPanel = new JPanel();
        this.initPanel.setSize(520,520);
        this.statusLabel = new JLabel("<html><body style='text-align:center;'>Hello " + this.controllerObject.getPlayer().getName() + "<br>we need a second player...</body></html>");
        // this.newGameBtn.addActionListener(this.controllerObject);
        
        this.initPanel.add(this.statusLabel, BorderLayout.CENTER);
        
        // this.playerOneBtn = new JButton("Select Player 1");
        // this.playerOneBtn.addActionListener(this.controllerObject);
        // // controlsPanel.add(this.playerOneBtn);
        
        // this.playerTwoBtn = new JButton("Select Player 2");
        // this.playerTwoBtn.addActionListener(this.controllerObject);
        // controlsPanel.add(this.playerTwoBtn);
        
        // this.endGameBtn = new JButton("End Game");
        // this.endGameBtn.addActionListener(this.controllerObject);
        // this.endGameBtn.setVisible(false);
        // controlsPanel.add(this.endGameBtn);

        this.boardPanel = new JPanel();
        this.boardPanel.setSize(520, 520);
        this.boardPanel.setLayout(new GridLayout(this.boardSize, this.boardSize, 0, 0));
        this.boardPanel.setLayout(new GridLayout(8, 8, 0, 0));
        this.masterPanel.add(this.boardPanel, BorderLayout.WEST);
        // this.masterPanel.add(controlsPanel, BorderLayout.EAST);
        this.masterPanel.add(this.initPanel);
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
                    if(i < this.controllerObject.getModel().getBoardSize()/2-1) {
                        piece.setColor("red"); 
                        // piece.setPlayer(this.playerOne);
                        // this.playerOnePieces.add(piece);
                        this.squareBtns[i][j].setBoardPiece(piece);
                        this.squareBtns[i][j].setIcon(new ImageIcon(piece.getImage()));
                    } else if(i > this.controllerObject.getModel().getBoardSize()/2) {
                        piece.setColor("white"); 
                        // piece.setPlayer(this.playerTwo);
                        // this.playerTwoPieces.add(piece);
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

    public void movePiece(TileBtn prevTileBtn, TileBtn nextTileBtn) {
        this.takePiece(prevTileBtn);
        this.putPiece(nextTileBtn);
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
    
    public void setPieceInMove(Piece piece) {
        this.pieceInMove = piece;
    }

    public void nextTurn() {
        // if(this.controllerObject.getPlayerInTurn() == this.playerOne) {
        //     this.playerInTurn = this.playerTwo;
        // } else {
        //     this.playerInTurn = this.playerOne;
        // }
        // this.winnerLabel.setText(this.playerInTurn.getName() + "'s turn");
        // this.winnerLabel.setVisible(true);
    }

    public void takePiece(TileBtn tileBtn) {
        // if(tileBtn.getBoardPiece().getPlayer() == this.playerInTurn) {
        //     if(this.squareBtns[tileBtn.getRow()][tileBtn.getColumn()].getBoardPiece() != null) {
        //         this.pieceInMove = tileBtn.getBoardPiece();
        //         checkPossibleMoves();
        //         this.squareBtns[tileBtn.getRow()][tileBtn.getColumn()].removeBoardPiece();
        //     }
        // }
    }
    
    public void putPiece(TileBtn tileBtn) {
        if(this.possibleMoves.contains(tileBtn)) {
            // if(this.pieceInMove.getRow() != tileBtn.getRow() && this.pieceInMove.getColumn() != tileBtn.getColumn()) {
            //     this.nextTurn();
            // }
            hidePossibleMoves();

            if(this.eatMoves.size() > 0 && this.eatMoves.containsKey(tileBtn)) {
                checkIfAte(tileBtn);
            }

            if(this.squareBtns[tileBtn.getRow()][tileBtn.getColumn()].getBoardPiece() == null) {
                this.pieceInMove.setRow(tileBtn.getRow());
                this.pieceInMove.setColumn(tileBtn.getColumn());
                this.squareBtns[tileBtn.getRow()][tileBtn.getColumn()].setIcon(new ImageIcon(this.pieceInMove.getImage()));
                this.squareBtns[tileBtn.getRow()][tileBtn.getColumn()].setBoardPiece(this.pieceInMove);
                checkIfCrowned(this.squareBtns[tileBtn.getRow()][tileBtn.getColumn()]);
                this.pieceInMove = null;
                this.possibleMoves.clear();
                this.controllerObject.checkWinner();
                // checkIfPlayerCanEat();
            }
        }
    }

    public void showWinner(Player player) {
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
            // if(btn.getBoardPiece().getRow() == this.numerOfSquares-1) {
            //     btn.crownPiece();
            // }
        }
    }

    private void checkPossibleMoves() {
        System.out.println();
        this.squareBtns[this.pieceInMove.getRow()][this.pieceInMove.getColumn()].setBackground(Color.decode("#add8e6"));
        this.possibleMoves.add(this.squareBtns[this.pieceInMove.getRow()][this.pieceInMove.getColumn()]);
        System.out.println(this.pieceInMove.getRow());
        System.out.println(this.pieceInMove.getColumn());
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
        // if(this.pieceInMove.getRow()-1 >= 0  && this.pieceInMove.getColumn()+1 < this.numerOfSquares) {
        //     if(this.squareBtns[this.pieceInMove.getRow()-1][this.pieceInMove.getColumn()+1].getBoardPiece() == null) {
        //         this.squareBtns[this.pieceInMove.getRow()-1][this.pieceInMove.getColumn()+1].setBackground(Color.decode(movementColor));
        //         this.possibleMoves.add(this.squareBtns[this.pieceInMove.getRow()-1][this.pieceInMove.getColumn()+1]);
        //     } else {
        //         if(this.pieceInMove.getRow()-2 >= 0 && this.pieceInMove.getColumn()+2 < this.numerOfSquares) {
        //             if(!this.squareBtns[this.pieceInMove.getRow()-1][this.pieceInMove.getColumn()+1].getBoardPiece().isSamePlayer(this.pieceInMove)) {
        //                 if(this.squareBtns[this.pieceInMove.getRow()-2][this.pieceInMove.getColumn()+2].getBoardPiece() == null) {
        //                     this.squareBtns[this.pieceInMove.getRow()-2][this.pieceInMove.getColumn()+2].setBackground(Color.decode(movementColor));
        //                     this.possibleMoves.add(this.squareBtns[this.pieceInMove.getRow()-2][this.pieceInMove.getColumn()+2]);
        //                     this.eatMoves.put(this.squareBtns[this.pieceInMove.getRow()-2][this.pieceInMove.getColumn()+2], this.squareBtns[this.pieceInMove.getRow()-1][this.pieceInMove.getColumn()+1]);
        //                 }
        //             }
        //         }
        //     }
        // }
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
        // if(this.pieceInMove.getRow()+1 < this.numerOfSquares && this.pieceInMove.getColumn()-1 >= 0) {
        //     if(this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()-1].getBoardPiece() == null) {
        //         this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()-1].setBackground(Color.decode(movementColor));
        //         this.possibleMoves.add(this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()-1]);
        //     } else {
        //         if(this.pieceInMove.getRow()+2 < this.numerOfSquares && this.pieceInMove.getColumn()-2 >= 0) {
        //             if(!this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()-1].getBoardPiece().isSamePlayer(this.pieceInMove)) {
        //                 if(this.squareBtns[this.pieceInMove.getRow()+2][this.pieceInMove.getColumn()-2].getBoardPiece() == null) {
        //                     this.squareBtns[this.pieceInMove.getRow()+2][this.pieceInMove.getColumn()-2].setBackground(Color.decode(movementColor));
        //                     this.possibleMoves.add(this.squareBtns[this.pieceInMove.getRow()+2][this.pieceInMove.getColumn()-2]);
        //                     this.eatMoves.put(this.squareBtns[this.pieceInMove.getRow()+2][this.pieceInMove.getColumn()-2], this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()-1]);
        //                 }
        //             }
        //         }
        //     }
        // }
    }
    
    private void checkMoveQuadrant4() {
        // if(this.pieceInMove.getRow()+1 < this.numerOfSquares && this.pieceInMove.getColumn()+1 < this.numerOfSquares) {
        //     if(this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()+1].getBoardPiece() == null) {
        //         this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()+1].setBackground(Color.decode("#add8e6"));
        //         this.possibleMoves.add(this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()+1]);
        //     } else {
        //         if(this.pieceInMove.getRow()+2 < this.numerOfSquares && this.pieceInMove.getColumn()+2 < this.numerOfSquares) {
        //             if(!this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()+1].getBoardPiece().isSamePlayer(this.pieceInMove)) {
        //                 if(this.squareBtns[this.pieceInMove.getRow()+2][this.pieceInMove.getColumn()+2].getBoardPiece() == null) {
        //                     this.squareBtns[this.pieceInMove.getRow()+2][this.pieceInMove.getColumn()+2].setBackground(Color.decode("#add8e6"));
        //                     this.possibleMoves.add(this.squareBtns[this.pieceInMove.getRow()+2][this.pieceInMove.getColumn()+2]);
        //                     this.eatMoves.put(this.squareBtns[this.pieceInMove.getRow()+2][this.pieceInMove.getColumn()+2], this.squareBtns[this.pieceInMove.getRow()+1][this.pieceInMove.getColumn()+1]);
        //                 }
        //             }
        //         }
        //     }
        // }
    }

    private void checkIfAte(TileBtn tileBtn) {
        TileBtn ateTile = this.eatMoves.get(tileBtn);
        // if(ateTile.getBoardPiece().getPlayer().getPlayerNumber() == 1) {
        //     this.playerOne.removeStake(ateTile.getBoardPiece());
        // } else {
        //     this.playerTwo.removeStake(ateTile.getBoardPiece());
        // }
        this.squareBtns[ateTile.getRow()][ateTile.getColumn()].removeBoardPiece();
        this.eatMoves.clear();
    }

    private void checkIfPlayerCanEat() {
        // for(Piece piece : this.playerInTurn.getPieces()) {
        //     if(this.playerInTurn.getPlayerNumber() == 1) {
        //         checkEatQuadrant1(piece.getRow(), piece.getColumn());
        //         checkEatQuadrant2(piece.getRow(), piece.getColumn());
        //         if(piece.getIsKing()) {
        //             checkEatQuadrant3(piece.getRow(), piece.getColumn());
        //             checkEatQuadrant4(piece.getRow(), piece.getColumn());
        //         }
        //     } else {
        //         checkEatQuadrant3(piece.getRow(), piece.getColumn());
        //         checkEatQuadrant4(piece.getRow(), piece.getColumn());
        //         if(piece.getIsKing()) {
        //             checkEatQuadrant1(piece.getRow(), piece.getColumn());
        //             checkEatQuadrant2(piece.getRow(), piece.getColumn());
        //         }
        //     }
        // }
    }

    private void checkEatQuadrant1(int row, int column) {
        // if(row-1 >= 0  && column+1 < this.numerOfSquares) {
        //     if(this.squareBtns[row-1][column+1].getBoardPiece() != null) {
        //         if(row-2 >= 0 && column+2 < this.numerOfSquares) {
        //             if(this.squareBtns[row-1][column+1].getBoardPiece().getPlayer() != this.playerInTurn) {
        //                 if(this.squareBtns[row-2][column+2].getBoardPiece() == null) {
        //                     this.squareBtns[row-2][column+2].setBackground(Color.RED);
        //                     this.possibleMoves.add(this.squareBtns[row-2][column+2]);
        //                 }
        //             }
        //         }
        //     }
        // }
    }

    private void checkEatQuadrant2(int row, int column) {
        // if(row-1 >= 0 && column-1 >= 0) {
        //     if(this.squareBtns[row-1][column-1].getBoardPiece() != null) {
        //         if(row-2 >= 0 && column-2 >= 0) {
        //             if(this.squareBtns[row-1][column-1].getBoardPiece().getPlayer() != this.playerInTurn) {
        //                 if(this.squareBtns[row-2][column-2].getBoardPiece() == null) {
        //                     this.squareBtns[row-2][column-2].setBackground(Color.RED);
        //                     this.possibleMoves.add(this.squareBtns[row-2][column-2]);
        //                 }
        //             }
        //         }
        //     }
        // }
    }

    private void checkEatQuadrant3(int row, int column) {
        // if(row+1 < this.numerOfSquares && column-1 >= 0) {
        //     if(this.squareBtns[row+1][column-1].getBoardPiece() != null) {
        //         if(row+2 < this.numerOfSquares && column-2 >= 0) {
        //             if(this.squareBtns[row+1][column-1].getBoardPiece().getPlayer() != this.playerInTurn) {
        //                 if(this.squareBtns[row+2][column-2].getBoardPiece() == null) {
        //                     this.squareBtns[row+2][column-2].setBackground(Color.RED);
        //                     this.possibleMoves.add(this.squareBtns[row+2][column-2]);
        //                 }
        //             }
        //         }
        //     }
        // }
    }
    
    private void checkEatQuadrant4(int row, int column) {
        // if(row+1 < this.numerOfSquares && column+1 < this.numerOfSquares) {
        //     if(this.squareBtns[row+1][column+1].getBoardPiece() != null) {
        //         if(row+2 < this.numerOfSquares && column+2 < this.numerOfSquares) {
        //             if(this.squareBtns[row+1][column+1].getBoardPiece().getPlayer() != this.playerInTurn) {
        //                 if(this.squareBtns[row+2][column+2].getBoardPiece() == null) {
        //                     this.squareBtns[row+2][column+2].setBackground(Color.RED);
        //                     this.possibleMoves.add(this.squareBtns[row+2][column+2]);
        //                 }
        //             }
        //         }
        //     }
        // }
    }

}