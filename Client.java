import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private Socket server = null;
    private ObjectOutputStream outputStream;
    private String name;
    private Controller controllerObject;

    public Client() {

        this.controllerObject = new Controller(this);

        connect();

        try {
            outputStream = new ObjectOutputStream(server.getOutputStream());
        }catch(IOException e) {
            e.printStackTrace();
        }

        ReadWorker rw = new ReadWorker(server,this);
        rw.execute();

        System.out.println("HERE");
    }

    public Controller getController() {
        return this.controllerObject;
    }
    
    public ObjectOutputStream getOutputStream() {
        return this.outputStream;
    }

    private void connect() {
        try {
            server = new Socket("127.0.0.1",8765);
            System.out.println("Connected");
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }
    
}