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
                if(updater.getGameState() == 0) {
                    this.parent.getController().showGameScreen(updater.getNumOfPlayer());
                } else if(updater.getGameState() == 1) {
                    System.out.println("Starting Game");
                    this.parent.getController().startGame(updater.getBoardSize());
                } else if(updater.getGameState() == 2) { 
                    this.parent.getController().movePiece(updater.getPrevRow(), updater.getPrevColumn(), updater.getNewRow(), updater.getNewColumn());
                } else if(updater.getGameState() == 3) { 
                    this.parent.getController().printWinner(updater.getNumOfPlayer());
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