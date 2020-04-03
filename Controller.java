
import java.awt.event.*;
import java.util.ArrayList;

public class Controller implements ActionListener {

    private Model modelObject;
    private Board board;
    private ArrayList<Player> players;
    private boolean gameRunning;

    public Controller(Model modelObject) {
        this.gameRunning = false;
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
        for(int i = 0; i < this.modelObject.getNumberOfPlayers(); i++) {
            this.players.add(new Player(Board.askNameView(i+1), i + 1));
        }
        this.board = new Board(this);
        board.setVisible(true);                
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof TileBtn) {
            if(this.board.getPieceInMove() != null) {
                this.board.putPiece((TileBtn) e.getSource());
            } else {
                this.board.takePiece((TileBtn) e.getSource());
            }
        } else {
            if(e.getSource() == this.board.getNewGameBtn()) {
                System.out.println("NEW GAME!!!");
                this.board.setNewGame();
            } else if(e.getSource() == this.board.getEndGameBtn()) {
                System.out.println("end game :(");
                this.board.endGame();
            }
        }
    }

    public void checkWinner() {
        if(this.getPlayers().get(0).getPieces().size() <= 0) {
            this.board.showWinner(this.getPlayers().get(1));
        } else if (this.getPlayers().get(1).getPieces().size() <= 0) {
            this.board.showWinner(this.getPlayers().get(0));
        }
    }

}