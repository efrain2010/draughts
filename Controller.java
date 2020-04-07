
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class Controller implements ActionListener {

    private Model modelObject;
    private Board board;
    private ArrayList<Player> players;
    private int connectedPlayer;
    private Client parent;
    private Player player;
    private int playerInTurn;
    
    public Controller(Client parent) {
        this.parent = parent;
        this.board = new Board(this);
        this.playerInTurn = 1;
    }
    
    public void showGameScreen(int playerNumber) {
        this.player.setPlayerNumber(playerNumber);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public void addPlayer(Player player) {
        // this.player.setPlayerNumber(playerNumber);
        // try {
        //     this.parent.getOutputStream().writeObject(new ModelUpdater(player.getName()));
        // } catch (IOException ex) {
        //     ex.printStackTrace();
        // }
    }

    public Model getModel() {
        return modelObject;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    
    public void setPlayer(int connectedPlayer) {
        this.connectedPlayer = connectedPlayer;
    }

    public void startGame(int boardSize) {
        // for(int i = 0; i < this.modelObject.getNumberOfPlayers(); i++) {
        // this.connectedPlayer = new Player(Board.askNameView());
        // this.players.add(connectedPlayer);
        // }
        this.board.startGame(boardSize);
    }

    public void movePiece(int prevRow, int prevColumn, int newRow, int newColumn) {
        System.out.println("moving the piece");
        this.board.movePiece(prevRow, prevColumn, newRow, newColumn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof TileBtn) {
            // if (this.board.getPieceInMove() != null) {
            //     try {
            //         TileBtn btn = (TileBtn) e.getSource();
            //         ModelUpdater boardUp = new ModelUpdater(btn.getRow(), btn.getColumn());
            //         boardUp.setGameState(2);
            //         this.parent.getOutputStream().writeObject(boardUp);
            //     } catch (IOException ex) {
            //         ex.printStackTrace();
            //     }
            // } else {
                if(this.playerInTurn == this.player.getPlayerNumber()) {
                    TileBtn btn = (TileBtn) e.getSource();
                    if(this.board.getPieceInMove() == null) {
                        if(btn.getBoardPiece() != null) {
                            if(btn.getBoardPiece().getPlayer() != null) {
                                System.out.println("Taking the piece");
                                System.out.println("x: " + btn.getBoardPiece().getRow() + " y: " + btn.getBoardPiece().getColumn());
                                this.board.setPieceInMove(btn.getBoardPiece());
                            }
                        }
                    } else {
                        System.out.println("Taking the piece");
                        ModelUpdater boardUp = new ModelUpdater(this.board.getPieceInMove().getRow(), this.board.getPieceInMove().getColumn(), btn.getRow(), btn.getColumn());
                        boardUp.setGameState(2);
                        this.board.setPieceInMove(null);
                        try {
                            this.parent.getOutputStream().writeObject(boardUp);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("Is not your turn");
                }
            // }
        } else {
            if(e.getSource() == this.board.getPlayerOneBtn()) {
                // this.connectedPlayer.setPlayerNumber(1);
                // this.board.setPlayer(1, this.connectedPlayer);
            } else if(e.getSource() == this.board.getPlayerTwoBtn()) {
                // this.connectedPlayer.setPlayerNumber(2);
                // this.board.setPlayer(2, this.connectedPlayer);
            } else if(e.getSource() == this.board.getNewGameBtn()) {
                System.out.println(this.connectedPlayer);
                System.out.println("NEW GAME!!!");
                this.board.setNewGame();
            } else if(e.getSource() == this.board.getEndGameBtn()) {
                System.out.println("end game :(");
                this.board.endGame();
            }
        }
    }

    // public void movePiece(TileBtn prevBtn, TileBtn newBtn) {
    //     this.board.takePiece(prevBtn);
    //     this.board.putPiece(newBtn);
    // }

    public void showCoords(int[] coords) {
        System.out.println(coords[0]);
    }

    public void checkWinner() {
        // if(this.board.getPlayerOne().getPieces().size() <= 0) {
        //     this.board.showWinner(this.board.getPlayerTwo());
        // } else if (this.board.getPlayerTwo().getPieces().size() <= 0) {
        //     this.board.showWinner(this.board.getPlayerOne());
        // }
    }

    public void changeTurn() {
        if(this.playerInTurn == 1) {
            this.playerInTurn = 2;
        } else {
            this.playerInTurn = 1;
        }
    }

}