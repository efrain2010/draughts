import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientRunner implements Runnable {

    private Socket s = null;
    private Server parent = null;
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;

    public ClientRunner(Socket s,Server parent) {
        this.s = s;
        this.parent = parent;
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
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}