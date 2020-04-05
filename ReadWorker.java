import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

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
        BoardUpdater updater = null;
        try {
            while ((updater = (BoardUpdater) inputStream.readObject()) != null) {
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }

    // protected void done() {
    //     synchronized (this.socket) {
    //         try {
    //             this.socket.wait();
    //             System.out.println(this.getState());
    //             System.out.println(this.toString());
    //             this.wait();
    //         } catch (InterruptedException e) {
    //             // TODO Auto-generated catch block
    //             e.printStackTrace();
    //         }
    //     }
    // }

}