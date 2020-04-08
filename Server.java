import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{
    
    private ServerSocket server;
    private ArrayList<ClientRunner> clients = new ArrayList<ClientRunner>();
    private Model modelObject;
    
    public Server() {
        this.modelObject = new Model();
        try {
            server = new ServerSocket(8765);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(true) {
            Socket clientSocket = null;
            try {
                if(this.clients.size() < this.modelObject.getNumberOfPlayers()) {
                    clientSocket = server.accept();
                    System.out.println("New client connected");
                    int playerNumber = this.clients.size()+1;
                    ClientRunner client = new ClientRunner(clientSocket, this);
                    this.clients.add(client);
                    Thread t = new Thread(client, ""+playerNumber);
                    t.start();
                    createPlayer(client, playerNumber);
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createPlayer(ClientRunner client, int playerNum) {
        ModelUpdater modelUpdater = new ModelUpdater(playerNum);
        this.modelObject.addPlayer(new Player(playerNum));
        client.transmitMessage(modelUpdater);
        if(this.clients.size() == this.modelObject.getNumberOfPlayers()) {
            this.modelObject.setGameState(1);
            modelUpdater.setGameState(1);
            modelUpdater.setBoardSize(this.modelObject.getBoardSize());
            transmit(modelUpdater);
        }
    }

    public void updateModel(ModelUpdater updater) {
        System.out.println(updater.getGameState());
        if(updater.getGameState() == 2) {
            if(this.modelObject.getPlayerInTurn() == 1) {
                this.modelObject.setPlayerInTurn(2);
            } else {
                this.modelObject.setPlayerInTurn(1);
            }
        } else if(updater.getGameState() == 3) {
            // for(Player player : this.modelObject.getPlayers()) {
            if(updater.getNumOfPlayer() == 1) {
                updater.setNumOfPlayer(2);
            } else {
                updater.setNumOfPlayer(1);
            }
            // }
        }
        this.transmit(updater);
    }

    public void transmit(ModelUpdater updater) {
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