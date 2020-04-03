
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Controller implements ActionListener {

    private Model modelObject;
    private Board board;
    private ArrayList<Player> players;
    private Player connectedPlayer;

    private ObjectOutputStream outputStream;

    public Controller(Model modelObject, ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
        this.modelObject = modelObject;
        this.players = new ArrayList<Player>();
        startGame();
    }
    
    public Controller(Model modelObject) {
        this.modelObject = modelObject;
        this.players = new ArrayList<Player>();
        startGame();
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

    public void startGame() {
        // for(int i = 0; i < this.modelObject.getNumberOfPlayers(); i++) {
            this.connectedPlayer = new Player(Board.askNameView());
            this.players.add(connectedPlayer);
        // }
        this.board = new Board(this);
        board.setVisible(true);                
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof TileBtn) {
            if(this.board.getPieceInMove() != null) {
                try {
                    TileBtn btn = (TileBtn) e.getSource();
                    System.out.println(this.board.getPieceInMove().getRow());
                    System.out.println(btn.getRow());
                    System.out.println(this.board.getPieceInMove().getColumn());
                    System.out.println(btn.getColumn());
                    BoardUpdater boardUp = new BoardUpdater(this.board.getPieceInMove().getRow(), btn.getRow(), this.board.getPieceInMove().getColumn(), btn.getColumn());
                    outputStream.writeObject(boardUp);
                }catch(IOException ex) {
                    ex.printStackTrace();
                }
                this.board.putPiece((TileBtn) e.getSource());
            } else {
                this.board.takePiece((TileBtn) e.getSource());
            }
        } else {
            if(e.getSource() == this.board.getPlayerOneBtn()) {
                this.connectedPlayer.setPlayerNumber(1);
                this.board.setPlayer(1, this.connectedPlayer);
            } else if(e.getSource() == this.board.getPlayerTwoBtn()) {
                this.connectedPlayer.setPlayerNumber(2);
                this.board.setPlayer(2, this.connectedPlayer);
            } else if(e.getSource() == this.board.getNewGameBtn()) {
                System.out.println("NEW GAME!!!");
                this.board.setNewGame();
            } else if(e.getSource() == this.board.getEndGameBtn()) {
                System.out.println("end game :(");
                this.board.endGame();
            }
        }
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