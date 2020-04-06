import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.SwingWorker;

public class ReadWorker extends SwingWorker<Void, Void> {

    private Socket socket = null;
    private ObjectInputStream inputStream = null;
    private Client parent;

    public ReadWorker(Socket s, Client parent) {
        this.socket = s;
        this.parent = parent;
        try {
            inputStream = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Void doInBackground() {
        System.out.println("Started read worker");
        ModelUpdater updater = null;
        try {
            while ((updater = (ModelUpdater) inputStream.readObject()) != null) {
                System.out.println(updater.getGameState());
                if(updater.getGameState() == 0) {
                    this.parent.getController().showGameScreen(updater.getNumOfPlayer());
                } else if(updater.getGameState() == 1) {
                    System.out.println("Starting Game");
                    this.parent.getController().startGame(updater.getBoardSize());
                } else if(updater.getGameState() == 2) { 
                    if(!updater.getSendingCoords()) {
                        System.out.println(updater);
                        this.parent.getController().setPlayer(updater.getNumOfPlayer());
                    } else {
                        TileBtn prevTileBtn = (TileBtn) this.parent.getController().getBoard().getSquareBtns()[updater.getPrevRow()][updater.getPrevColumn()];
                        TileBtn nextTileBtn = (TileBtn) this.parent.getController().getBoard().getSquareBtns()[updater.getNewRow()][updater.getNewColumn()];
                        this.parent.getController().movePiece(prevTileBtn, nextTileBtn);
                        // this.parent.getController().getBoard().movePieceByCoords(updater.getPrevRow(),updater.getPrevColumn());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }

}