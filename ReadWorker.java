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
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public Void doInBackground() {
        System.out.println("Started read worker");
        BoardUpdater updater = null;
        try {
            while((updater = (BoardUpdater)inputStream.readObject())!= null) {
                TileBtn prevTileBtn = (TileBtn) this.parent.getController().getBoard().getSquareBtns()[updater.getPrevRow()][updater.getPrevColumn()];
                TileBtn nextTileBtn = (TileBtn) this.parent.getController().getBoard().getSquareBtns()[updater.getPrevRow()][updater.getPrevColumn()];
                System.out.println(prevTileBtn.getBoardPiece());
                this.parent.getController().getBoard().movePiece(prevTileBtn, nextTileBtn);
            }
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            return null;
        }
    }

}