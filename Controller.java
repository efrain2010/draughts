
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Controller implements ActionListener {

    private Model modelObject;
    private Board board;
    private ArrayList<Player> players;
    private int connectedPlayer;
    private Client parent;
    private Player player;
    private ArrayList<Piece> playerOnePieces, playerTwoPieces;

    public Controller(Client parent) {
        this.parent = parent;
        this.board = new Board(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof TileBtn) {
            if (this.board.getPieceInMove() != null) {
                try {
                    TileBtn btn = (TileBtn) e.getSource();
                    ModelUpdater boardUp = new ModelUpdater(this.board.getPieceInMove().getRow(), btn.getRow(), this.board.getPieceInMove().getColumn(), btn.getColumn());
                    this.parent.getOutputStream().writeObject(boardUp);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                TileBtn btn = (TileBtn) e.getSource();
                this.getBoard().setPieceInMove(btn.getBoardPiece());
            }
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

    public void movePiece(TileBtn prevBtn, TileBtn newBtn) {
        this.board.takePiece(prevBtn);
        this.board.putPiece(newBtn);
    }

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

}