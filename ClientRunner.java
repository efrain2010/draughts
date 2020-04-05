import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientRunner implements Runnable {

    private Socket s = null;
    private Server parent = null;
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;
    private int player;

    public ClientRunner(Socket s,Server parent, int player) {
        this.s = s;
        this.parent = parent;
        this.player = player;
        try {
            outputStream = new ObjectOutputStream(this.s.getOutputStream());
            inputStream = new ObjectInputStream(this.s.getInputStream());
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void run() {
        // receive messages
        try {
            BoardUpdater updater = null;
            // cambiar esto a la clase que lo controlará
            while((updater = (BoardUpdater)inputStream.readObject())!= null) {
                System.out.println(player);
                this.parent.transmit(updater);
            }
            inputStream.close();
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void transmitMessage(BoardUpdater updater) {
        try {
            outputStream.writeObject(updater);
            outputStream.reset();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}