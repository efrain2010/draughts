
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

    public int getPlayerInTurn() {
        return playerInTurn;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
        this.board.startGame(boardSize);
    }

    public void movePiece(int prevRow, int prevColumn, int newRow, int newColumn) {
        this.board.movePiece(prevRow, prevColumn, newRow, newColumn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof TileBtn) {
            if(this.playerInTurn == this.player.getPlayerNumber()) {
                TileBtn btn = (TileBtn) e.getSource();
                if(this.board.getPieceInMove() == null) {
                    if(btn.getBoardPiece() != null) {
                        if(btn.getBoardPiece().getPlayer() != null) {
                            this.board.setPieceInMove(btn.getBoardPiece());
                            this.board.checkPossibleMoves();
                        }
                    }
                } else {
                    if(this.board.getPossibleMoves().contains(btn)) {
                        ModelUpdater boardUp = new ModelUpdater(this.board.getPieceInMove().getRow(), this.board.getPieceInMove().getColumn(), btn.getRow(), btn.getColumn());
                        boardUp.setGameState(2);
                        this.board.setPieceInMove(null);
                        try {
                            this.parent.getOutputStream().writeObject(boardUp);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } else {
                System.out.println("Is not your turn");
            }
        } else {
            if(e.getSource() == this.board.getNewGameBtn()) {
                System.out.println("NEW GAME!!!");
                ModelUpdater boardUp = new ModelUpdater(this.player.getPlayerNumber());
                boardUp.setGameState(1);
                try {
                    this.parent.getOutputStream().writeObject(boardUp);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void showCoords(int[] coords) {
        System.out.println(coords[0]);
    }

    public void showLooser(int playerNumber) {
        System.out.println("Sending looser player " + playerNumber);
        ModelUpdater boardUp = new ModelUpdater(playerNumber);
        boardUp.setGameState(3);
        try {
            this.parent.getOutputStream().writeObject(boardUp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // if(this.board.getPlayerOne().getPieces().size() <= 0) {
        //     this.board.showWinner(this.board.getPlayerTwo());
        // } else if (this.board.getPlayerTwo().getPieces().size() <= 0) {
        //     this.board.showWinner(this.board.getPlayerOne());
        // }
    }

    public void printWinner(int winnerPlayerNumber) {
        this.board.printWinner(winnerPlayerNumber);
    }

    public void changeTurn() {
        if(this.playerInTurn == 1) {
            this.playerInTurn = 2;
        } else {
            this.playerInTurn = 1;
        }

        if(this.itIsMyTurn()) {
            this.board.checkIfPlayerCanEat();
        }
    }

    public boolean itIsMyTurn() {
        return this.player.getPlayerNumber() == this.playerInTurn;
    }

}