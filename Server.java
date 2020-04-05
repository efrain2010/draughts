import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{
    
    private ServerSocket server;
    private ArrayList<ClientRunner> clients = new ArrayList<ClientRunner>();
    private Game gameObject;
    protected Thread runningThread;
    protected boolean isStopped = false;
    private ArrayList<Player> players = new ArrayList<Player>(); 
    
    public Server() {
        this.gameObject = new Game();
        try {
            server = new ServerSocket(8765);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        while(!this.isStopped()) {
            Socket clientSocket = null;
            try {
                
                if(clients.size() < 2) {
                    clientSocket = server.accept();
                    System.out.println("New client connected");
                    int playerNum = clients.size()+1;
                    ClientRunner client = new ClientRunner(clientSocket,this, playerNum);
                    clients.add(client);
                    Thread r = new Thread(client, ""+playerNum);
                    r.start();
                    this.transmit(new BoardUpdater(playerNum));
                }
            }catch(IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    return;
                }
                e.printStackTrace();
            }
        }
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.server.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }
    
    public void transmit(BoardUpdater updater) {
        for(ClientRunner c: clients) {
            if(c != null) {
                c.transmitMessage(updater);
            }
        }
    }
    
    public static void main(String[] args) {

        Thread t = new Thread(new Server());
        t.start();
        try {
            t.join();
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        
    }

}